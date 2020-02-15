package repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.startingData.DataInitializer;
import harryPotterApp.startingData.EntityManagerFactory;
import org.junit.jupiter.api.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CharacterRepositoryImplTest {

    private static EntityManager em;
    private CharacterRepository characterRepository;
    private static Long personId;

    @BeforeAll
    static void createData(){
        em = EntityManagerFactory.getEmf().createEntityManager();
        DataInitializer.addAllData(em);
    }

    @BeforeEach
    void setUp() {
        em = EntityManagerFactory.getEmf().createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
    }

    @AfterEach
    void cleanUp() {
        em.close();
    }


    @Order(1)
    @Test
    void shouldAddCreatedCharacterToDB() {
        EntityTransaction transaction = em.getTransaction();
        LocalDate birthDate = LocalDate.of(1980, 7, 31);
        HPCharacter hermionaGranger = new HPCharacter("Hermiona", "Granger", birthDate);

        transaction.begin();
        HPCharacter savedHarryPotter = characterRepository.add(hermionaGranger);
        transaction.commit();
        personId = savedHarryPotter.getId();
        assertThat(savedHarryPotter.getId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    void shouldFindCharacterById() {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        HPCharacter foundCharacter = characterRepository.findById(personId);
        transaction.commit();

        assertThat(foundCharacter).isNotNull();
    }

    @Order(3)
    @Test
    void shouldModifyCharacter() {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter characterToModify = characterRepository.findById(personId);
        String newNameForCharacter = "Barry";


//        em.detach(characterToModify);
        characterToModify.setFirstName(newNameForCharacter);

        transaction.begin();
        characterRepository.update(characterToModify);
        transaction.commit();
        /*em.close();

        characterRepository = new CharacterRepositoryImpl(emf.createEntityManager());*/
        HPCharacter characterAfterMerge = characterRepository.findById(personId);

        assertThat(characterAfterMerge.getFirstName()).isEqualTo(newNameForCharacter);

    }

    @Order(4)
    @Test
    void shouldDeleteCharacter() {
        EntityTransaction transaction = em.getTransaction();
        Long id = 1L;

        transaction.begin();
        characterRepository.deleteById(id);
        transaction.commit();

        HPCharacter characterAfterDelete = characterRepository.findById(id);
        assertThat(characterAfterDelete).isNull();
    }

}
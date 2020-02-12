package repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import org.junit.jupiter.api.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CharacterRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private CharacterRepository characterRepository;
    private static Long personId;

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
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
        HPCharacter harryPotter = new HPCharacter("Harry", "Potter", birthDate);

        transaction.begin();
        HPCharacter savedHarryPotter = characterRepository.add(harryPotter);
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
        HPCharacter characterToDelete = characterRepository.findById(personId);

        transaction.begin();
        characterRepository.delete(characterToDelete);
        transaction.commit();

        HPCharacter characterAfterDelete = characterRepository.findById(personId);
        assertThat(characterAfterDelete).isNull();
    }

}
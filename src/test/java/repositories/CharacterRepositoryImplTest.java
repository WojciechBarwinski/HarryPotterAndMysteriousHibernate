package repositories;

import entities.HPCharacter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CharacterRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private CharacterRepository characterRepository;

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
        HPCharacter savedHarryPotter = characterRepository.create(harryPotter);
        transaction.commit();

        assertThat(savedHarryPotter.getId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    void shouldFindCharacterById() {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        HPCharacter foundCharacter = characterRepository.findById(1L);
        transaction.commit();

        assertThat(foundCharacter).isNotNull();
//        assertThat(foundCharacter.getFirstName()).isEqualTo("Harry");
    }

    @Order(3)
    @Test
    void shouldModifyCharacter() {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter characterToModify = characterRepository.findById(1L);
        String newNameForCharacter = "Barry";


        em.detach(characterToModify);
        characterToModify.setFirstName(newNameForCharacter);

        transaction.begin();
        characterRepository.modify(characterToModify);
        transaction.commit();
        em.close();

        characterRepository = new CharacterRepositoryImpl(emf.createEntityManager());
        HPCharacter characterAfterMerge = characterRepository.findById(1L);

        assertThat(characterAfterMerge.getFirstName()).isEqualTo(newNameForCharacter);

    }
    @Order(4)
    @Test
    void shouldDeleteCharacter(){
        EntityTransaction transaction = em.getTransaction();
        HPCharacter characterToDelete = characterRepository.findById(1L);

        transaction.begin();
        characterRepository.delete(characterToDelete);
        transaction.commit();

        HPCharacter characterAfterDelete = characterRepository.findById(1L);
        assertThat(characterAfterDelete).isNull();

    }
}
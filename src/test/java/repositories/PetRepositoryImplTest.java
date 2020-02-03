package repositories;

import entities.HPCharacter;
import entities.Pet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PetRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private PetRepository petRepository;
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp(){
        em = emf.createEntityManager();
        petRepository = new PetRepositoryImpl(em);
        characterRepository = new CharacterRepositoryImpl(em);
    }

    @AfterEach
    public void cleanUp(){
        em.close();
    }

    @Test
    public void shouldAddPetToTheDB(){
        EntityTransaction transaction = em.getTransaction();
        LocalDate birthDate = LocalDate.of(1980, 7, 31);
        HPCharacter harryPotter = new HPCharacter("Harry", "Potter", birthDate);
        Pet pet = new Pet("Hedwig", harryPotter, "Owl");

        transaction.begin();
        HPCharacter savedHarryPotter = characterRepository.create(harryPotter);
        transaction.commit();
        transaction.begin();
        Pet savedHedwig = petRepository.add(pet);
        transaction.commit();

        assertThat(savedHarryPotter.getId()).isGreaterThan(0);
        assertThat(savedHedwig.getId()).isGreaterThan(0);

    }
}
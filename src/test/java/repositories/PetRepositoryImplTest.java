package repositories;

import entities.HPCharacter;
import entities.Pet;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PetRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private PetRepository petRepository;
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        petRepository = new PetRepositoryImpl(em);
        characterRepository = new CharacterRepositoryImpl(em);
    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }

    @Order(1)
    @Test
    public void shouldAddPetToTheDB() {
        EntityTransaction transaction = em.getTransaction();
        LocalDate birthDate = LocalDate.of(1980, 7, 31);
        HPCharacter harryPotter = new HPCharacter("Harry", "Potter", birthDate);
        Pet pet = new Pet("Hedwig", harryPotter, "Owl");

        transaction.begin();
        HPCharacter savedHarryPotter = characterRepository.add(harryPotter);
        transaction.commit();
        transaction.begin();
        Pet savedHedwig = petRepository.add(pet);
        transaction.commit();

        assertThat(savedHarryPotter.getId()).isGreaterThan(0);
        assertThat(savedHedwig.getId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    void shouldRemovePetWhenOwnerIsRemoved() {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter harryFromDB = characterRepository.findById(1L);
        Pet hedwigFromDb = petRepository.findById(1L);

        transaction.begin();
        characterRepository.delete(harryFromDB);
        transaction.commit();
        Pet deletedPet = petRepository.findById(1L);

        assertThat(deletedPet).isNull();
    }
    @Order(3)
    @Test
    void shouldNotRemoveCharacterWhenPetIsRemoved(){
        EntityTransaction transaction = em.getTransaction();
        LocalDate birthDate = LocalDate.of(1980, 7, 31);
        HPCharacter harryPotter = new HPCharacter("Harry", "Potter", birthDate);
        Pet pet = new Pet("Hedwig", harryPotter, "Owl");

        transaction.begin();
        HPCharacter savedHarryPotter = characterRepository.add(harryPotter);
        Pet savedHedwig = petRepository.add(pet);
        transaction.commit();

        transaction.begin();
        petRepository.deletedById(2L);
        transaction.commit();
        HPCharacter characterNotToBeDelete = characterRepository.findById(2L);

        assertThat(characterNotToBeDelete).isNotNull();
    }
}
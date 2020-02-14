package repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.Pet;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.PetRepository;
import harryPotterApp.repositories.PetRepositoryImpl;
import harryPotterApp.startingData.DataInitializer;
import harryPotterApp.startingData.SingletonEntityManagerFactory;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PetRepositoryImplTest {

    private static EntityManager em;
    private PetRepository petRepository;
    private CharacterRepository characterRepository;

    @BeforeAll
    static void createData(){
        em = SingletonEntityManagerFactory.getEmf().createEntityManager();
        DataInitializer.addAllData(em);
    }

    @BeforeEach
    public void setUp() {
        em = SingletonEntityManagerFactory.getEmf().createEntityManager();
        petRepository = new PetRepositoryImpl(em);
        characterRepository = new CharacterRepositoryImpl(em);
    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }


    @Order(1)
    @Test
    public void shouldReturnListOfCharacterWithoutPet(){
        List<HPCharacter> allCharactersWithoutPet = petRepository.getAllCharactersWithoutPet();
        assertThat(allCharactersWithoutPet.size()).isGreaterThan(0);
    }

    /*@Order(3)
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
    }*/
}
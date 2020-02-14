package repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;
import harryPotterApp.repositories.*;
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
class HPLocationRepositoryImplTest {
    private static EntityManager em;
    private CharacterRepository characterRepository;
    private HPLocationRepository locationRepository;

    @BeforeAll
    static void createData(){
        em = SingletonEntityManagerFactory.getEmf().createEntityManager();
        DataInitializer.addAllData(em);
    }

    @BeforeEach
    public void setUp() {
        em = SingletonEntityManagerFactory.getEmf().createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        locationRepository = new HPLocationRepositoryImpl(em);

    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }

    @Order(1)
    @Test
    public void shouldReturnLocationByCharacterId(){
        Long dumbledoreId = 2L;
        HPLocation foundedLocation = locationRepository.findByCharacterId(dumbledoreId);
        assertThat(foundedLocation.getLocationName()).isEqualTo("Hogwarts");
    }

    @Order(3)
    @Test
    public void shouldReturnAllCharacterFromLocation(){
        Long hogwartsId = 1L;
        List<HPCharacter> residents = locationRepository.getAllResidents(hogwartsId);
        em.close();

        assertThat(residents.size()).isEqualTo(3);
    }


    }
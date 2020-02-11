package repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.HPLocationRepository;
import harryPotterApp.repositories.HPLocationRepositoryImpl;
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
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private CharacterRepository characterRepository;
    private HPLocationRepository locationRepository;

    private HPCharacter albusDumbledore;
    private HPCharacter severusSnape;
    private HPCharacter harryPotter;

    private HPLocation hogwarts;
    private HPLocation malfoyManor;
    private HPLocation hagridsHouse;

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        locationRepository = new HPLocationRepositoryImpl(em);

        albusDumbledore = new HPCharacter("Albus", "Dumbledore", LocalDate.of(1881, 6, 18));
        severusSnape = new HPCharacter("Severus", "Snape", LocalDate.of(1960, 6, 1));
        harryPotter = new HPCharacter("Harry", "Potter", LocalDate.of(1980, 7, 31));

        hogwarts = new HPLocation("Hogwarts");
        malfoyManor = new HPLocation("Malfoy Manor");
        hagridsHouse = new HPLocation("Hagrid's house");
    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }

    @Order(1)
    @Test
    public void shouldAddLocationToDB(){
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        locationRepository.addLocation(hogwarts);
        locationRepository.addLocation(malfoyManor);
        HPLocation hpLocation = locationRepository.addLocation(hagridsHouse);
        characterRepository.add(albusDumbledore);
        characterRepository.add(severusSnape);
        HPCharacter hpCharacter = characterRepository.add(harryPotter);
        transaction.commit();

        assertThat(hpLocation.getId()).isGreaterThan(0);
        assertThat(hpCharacter.getId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    public void shouldLinkCharacterWithLocation(){
        EntityTransaction transaction = em.getTransaction();
        HPCharacter character1 = characterRepository.findById(1L);
        HPCharacter character2 = characterRepository.findById(2L);
        HPCharacter character3 = characterRepository.findById(3L);
        HPLocation location1 = locationRepository.findLocationById(1L);
        HPLocation location2 = locationRepository.findLocationById(2L);
       character1.setLocation(location1);
       character2.setLocation(location1);
       character3.setLocation(location2);

        transaction.begin();
        characterRepository.updateById(1L);
        characterRepository.updateById(2L);
        characterRepository.updateById(3L);
        transaction.commit();
        HPLocation locationToCheck = locationRepository.findLocationById(1L);

        assertThat(locationToCheck.getResidents().size()).isEqualTo(2);
    }

    @Order(3)
    @Test
    public void shouldReturnAllCharacterFromLocation(){
        List<HPCharacter> residents = locationRepository.getAllResidents(1L);
        em.close();

        assertThat(residents.get(0).getFirstName()).isEqualTo("Albus");
        assertThat(residents.get(1).getFirstName()).isEqualTo("Severus");
    }


    }
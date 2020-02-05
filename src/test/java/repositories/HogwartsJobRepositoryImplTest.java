package repositories;

import entities.HPCharacter;
import entities.HogwartsJob;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HogwartsJobRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private CharacterRepository characterRepository;
    private HogwartsJobRepository hogwartsJobRepository;
    private HPCharacter albusDumbledore;
    private HPCharacter severusSnape;
    private HPCharacter harryPotter;

    private HogwartsJob elixirMaster;
    private HogwartsJob headmaster;
    private HogwartsJob houseHead;


    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        hogwartsJobRepository = new HogwartsJobRepositoryImpl(em);
        albusDumbledore = new HPCharacter("Albus", "Dumbledore", LocalDate.of(1881, 6, 18));
        severusSnape = new HPCharacter("Severus", "Snape", LocalDate.of(1960, 6, 1));
        harryPotter = new HPCharacter("Harry", "Potter", LocalDate.of(1980, 7, 31));

        elixirMaster = new HogwartsJob(BigDecimal.valueOf(5000), "Elixir Master");
        headmaster = new HogwartsJob(BigDecimal.valueOf(15000), "Headmaster of Hogwarts");
        houseHead = new HogwartsJob(BigDecimal.valueOf(7500), "House Head of Slytherin");
    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }

    @Order(1)
    @Test
    public void shouldAddThreeHPCharacterToDataBase() {
        EntityTransaction transaction = em.getTransaction();
        List<HPCharacter> addedHPCharacters = new ArrayList<>();

        transaction.begin();
        addedHPCharacters.add(characterRepository.create(albusDumbledore));
        addedHPCharacters.add(characterRepository.create(severusSnape));
        addedHPCharacters.add(characterRepository.create(harryPotter));
        transaction.commit();

        assertThat(addedHPCharacters.size()).isEqualTo(3);

    }

    @Order(2)
    @Test
    public void shouldAddThreeHogwartsJobsToDataBase() {
        EntityTransaction transaction = em.getTransaction();
        List<HogwartsJob> addedHogwartsJobs = new ArrayList<>();

        transaction.begin();
        addedHogwartsJobs.add(hogwartsJobRepository.create(elixirMaster));
        addedHogwartsJobs.add(hogwartsJobRepository.create(headmaster));
        addedHogwartsJobs.add(hogwartsJobRepository.create(houseHead));
        transaction.commit();

        assertThat(addedHogwartsJobs.size()).isEqualTo(3);
    }


    // TODO: 05.02.2020 added job TO character dont work correct
    @Order(3)
    @Test
    public void shouldCreateLinkBetweenOneCharacterAndOneHogwartsJobByCharacter() {
        EntityTransaction transaction = em.getTransaction();
        HogwartsJob hogwartsJobToLink = hogwartsJobRepository.readById(2L);
        HPCharacter characterToLink = characterRepository.findById(1L);


        /*characterToLink.setPositions(hogwartsJobToLink);
        transaction.begin();
        characterRepository.modify(characterToLink);
        transaction.commit();

        assertThat(characterToLink.getPositions().size()).isEqualTo(1);*/

        hogwartsJobToLink.setCharactersAsEmployee(characterToLink);
        transaction.begin();
        hogwartsJobRepository.upDate(hogwartsJobToLink);
        transaction.commit();

        assertThat(hogwartsJobToLink.getCharactersInJob().size()).isEqualTo(1);
    }

    @Order(4)
    @Test
    public void shouldCreateLinkBetweenOneHogwartsJobAndOneCharacterByJob() {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter characterToLink = characterRepository.findById(2L);
        HogwartsJob hogwartsJobToLink = hogwartsJobRepository.readById(1L);

        hogwartsJobToLink.setCharactersAsEmployee(characterToLink);
        transaction.begin();
        hogwartsJobRepository.upDate(hogwartsJobToLink);
        transaction.commit();

        assertThat(hogwartsJobToLink.getCharactersInJob().size()).isEqualTo(1);
    }

    @Order(5)
    @Test
    public void shouldAddSecondJobToCharacterByJob(){
        EntityTransaction transaction = em.getTransaction();
        HPCharacter characterToUpDate = characterRepository.findById(2L);
        HogwartsJob hogwartsJobToAdd = hogwartsJobRepository.readById(3L);

        hogwartsJobToAdd.setCharactersAsEmployee(characterToUpDate);
        transaction.begin();
        hogwartsJobRepository.upDate(hogwartsJobToAdd);
        transaction.commit();

        assertThat(hogwartsJobToAdd.getCharactersInJob().size()).isEqualTo(1);
    }

    @Order(6)
    @Test
    public void shouldAddSecondCharacterToJobByJob(){
        EntityTransaction transaction = em.getTransaction();
        HPCharacter characterToAdd = characterRepository.findById(3L);
        HogwartsJob employeeToUpDate = hogwartsJobRepository.readById(2L);

        employeeToUpDate.setCharactersAsEmployee(characterToAdd);
        transaction.begin();
        hogwartsJobRepository.upDate(employeeToUpDate);
        transaction.commit();

        assertThat(employeeToUpDate.getCharactersInJob().size()).isEqualTo(2);
    }

    @Order(7)
    @Test
    public void shouldReturnHogwartsJobByThisJob(){
        HogwartsJob jobToSearch = hogwartsJobRepository.readById(3L);

        HogwartsJob foundJob = hogwartsJobRepository.readByJob(jobToSearch);

        assertThat(foundJob).isEqualTo(jobToSearch);
    }

    //todo
    @Order(8)
    @Test
    public void shouldUpDateJob(){

    }
    //todo
    @Order(9)
    @Test
    public void shouldDeleteJobWithNoCharacter(){

    }
    //todo
    @Order(10)
    @Test
    public void shouldDeleteJobWithCharacterLinkedToThem(){

    }
    }




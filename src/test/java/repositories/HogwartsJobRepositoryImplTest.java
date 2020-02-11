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

        elixirMaster =
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
        addedHPCharacters.add(characterRepository.add(albusDumbledore));
        addedHPCharacters.add(characterRepository.add(severusSnape));
        addedHPCharacters.add(characterRepository.add(harryPotter));
        transaction.commit();

        HPCharacter hpCharacter1 = addedHPCharacters.get(0);
        HPCharacter hpCharacter2 = addedHPCharacters.get(1);
        HPCharacter hpCharacter3 = addedHPCharacters.get(2);

        assertThat(addedHPCharacters.size()).isEqualTo(3);
        assertThat(hpCharacter1.getId()).isGreaterThan(0);
        assertThat(hpCharacter2.getId()).isGreaterThan(0);
        assertThat(hpCharacter3.getId()).isGreaterThan(0);

    }

    @Order(2)
    @Test
    public void shouldAddThreeHogwartsJobsToDataBase() {
        EntityTransaction transaction = em.getTransaction();
        List<HogwartsJob> addedHogwartsJobs = new ArrayList<>();

        transaction.begin();
        addedHogwartsJobs.add(hogwartsJobRepository.add(headmaster));
        addedHogwartsJobs.add(hogwartsJobRepository.add(elixirMaster));
        addedHogwartsJobs.add(hogwartsJobRepository.add(houseHead));
        transaction.commit();

        HogwartsJob hogwartsJob1 = addedHogwartsJobs.get(0);
        HogwartsJob hogwartsJob2 = addedHogwartsJobs.get(1);
        HogwartsJob hogwartsJob3 = addedHogwartsJobs.get(2);

        assertThat(addedHogwartsJobs.size()).isEqualTo(3);
        assertThat(hogwartsJob1.getId()).isGreaterThan(0);
        assertThat(hogwartsJob2.getId()).isGreaterThan(0);
        assertThat(hogwartsJob3.getId()).isGreaterThan(0);
    }


    @Order(3)
    @Test
    public void shouldCreateLinkBetweenOneCharacterAndOneHogwartsJob() {
        EntityTransaction transaction = em.getTransaction();
        Long idJobToLink = 2L;
        Long idCharacterToLink = 2L;
        HogwartsJob hogwartsJobToLink = hogwartsJobRepository.findById(idJobToLink);
        HPCharacter characterToLink = characterRepository.findById(idCharacterToLink);

        hogwartsJobToLink.setCharactersAsEmployee(characterToLink);
        transaction.begin();
        hogwartsJobRepository.updateById(idJobToLink);
        transaction.commit();

        HogwartsJob hogwartsJobAfterUpdate = hogwartsJobRepository.findById(idJobToLink);
        assertThat(hogwartsJobAfterUpdate.getCharactersInJob().size()).isEqualTo(1);
    }

    @Order(4)
    @Test
    public void shouldAddSecondJobToCharacter() {
        EntityTransaction transaction = em.getTransaction();
        Long idJobToLink = 3L;
        Long idCharacterToLink = 2L;
        HogwartsJob hogwartsJobToLink = hogwartsJobRepository.findById(idJobToLink);
        HPCharacter characterToLink = characterRepository.findById(idCharacterToLink);

        hogwartsJobToLink.setCharactersAsEmployee(characterToLink);
        transaction.begin();
        hogwartsJobRepository.updateById(idJobToLink);
        characterRepository.update(characterToLink);
        transaction.commit();

        HPCharacter characterAfterUpdate = characterRepository.findById(idCharacterToLink);
        assertThat(characterAfterUpdate.getPositions().size()).isEqualTo(2);
    }


    @Order(5)
    @Test
    public void shouldChangeSalaryToNewValue(){
        EntityTransaction transaction = em.getTransaction();
        Long idJobToChange = 1L;
        HogwartsJob hogwartsJobToUpdate = hogwartsJobRepository.findById(idJobToChange);

        hogwartsJobToUpdate.setSalary(BigDecimal.valueOf(6666));
        transaction.begin();
        hogwartsJobRepository.updateById(idJobToChange);
        transaction.commit();

        HogwartsJob hogwartsJobAfterSalaryChange = hogwartsJobRepository.findById(idJobToChange);
        assertThat(hogwartsJobAfterSalaryChange.getSalary()).isEqualTo(BigDecimal.valueOf(6666));
    }

    //todo
    /*@Order(6)
    @Test
    public void shouldReturnJobByName(){
        EntityTransaction transaction = em.getTransaction();
        String jobName = "Elixir Master";
        HogwartsJob foundedJob = hogwartsJobRepository.findJobByName(jobName);

        assertThat(foundedJob.getPositionName()).isEqualTo(jobName);

    }*/
    @Order(10)
    @Test
    public void shouldDeleteJob(){
        EntityTransaction transaction = em.getTransaction();
        Long idJobDelete = 1L;

        transaction.begin();
        hogwartsJobRepository.deleteById(idJobDelete);
        transaction.commit();

        HogwartsJob hogwartsDeletedJob = hogwartsJobRepository.findById(idJobDelete);
        assertThat(hogwartsDeletedJob).isNull();
    }
    }




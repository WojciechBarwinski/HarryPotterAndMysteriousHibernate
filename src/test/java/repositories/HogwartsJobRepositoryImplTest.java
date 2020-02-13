package repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HogwartsJob;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.HogwartsJobRepository;
import harryPotterApp.repositories.HogwartsJobRepositoryImpl;
import harryPotterApp.startingData.DataInitializer;
import harryPotterApp.startingData.SingletonEntityManagerFactory;
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

    private static EntityManager em;
    private CharacterRepository characterRepository;
    private HogwartsJobRepository hogwartsJobRepository;

    @BeforeAll
    static void createData(){
        em = SingletonEntityManagerFactory.getEmf().createEntityManager();
        DataInitializer.addAllData(em);
    }
    @BeforeEach
    public void setUp() {
        em = SingletonEntityManagerFactory.getEmf().createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        hogwartsJobRepository = new HogwartsJobRepositoryImpl(em);
    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }


    @Order(1)
    @Test
    public void shouldReturnJobByCharacterId(){
        Long characterId = 2L;
        List<HogwartsJob> characterJobs = hogwartsJobRepository.findJobByIdCharacter(characterId);

        assertThat(characterJobs.get(0).getPositionName()).isEqualTo("Director of Hogwarts");

    }


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




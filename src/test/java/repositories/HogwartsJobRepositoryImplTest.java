package repositories;

import harryPotterApp.entities.HogwartsJob;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.HogwartsJobRepository;
import harryPotterApp.repositories.HogwartsJobRepositoryImpl;
import harryPotterApp.startingData.DataInitializer;
import harryPotterApp.startingData.EntityManagerFactory;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HogwartsJobRepositoryImplTest {

    private static EntityManager em;
    private CharacterRepository characterRepository;
    private HogwartsJobRepository hogwartsJobRepository;

    @BeforeAll
    static void createData(){
        em = EntityManagerFactory.getEmf().createEntityManager();
        DataInitializer.addAllData(em);
    }
    @BeforeEach
    public void setUp() {
        em = EntityManagerFactory.getEmf().createEntityManager();
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


    }




package repositories;

import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.StudentRepository;
import harryPotterApp.repositories.StudentRepositoryImpl;
import harryPotterApp.startingData.DataInitializer;
import harryPotterApp.startingData.EntityManagerFactory;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryImplTest {

    private static EntityManager em;
    private StudentRepository studentRepository;
    private CharacterRepository characterRepository;
    private static Long studentId;

    @BeforeAll
    static void createData(){
        em = EntityManagerFactory.getEmf().createEntityManager();
        DataInitializer.addAllData(em);
    }

    @BeforeEach
    void setUp() {
        em = EntityManagerFactory.getEmf().createEntityManager();
        studentRepository = new StudentRepositoryImpl(em);
        characterRepository = new CharacterRepositoryImpl(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
    }

   /* @Order(1)
    @Test
    public void shouldReturnStudentByCharacterId(){
        Long characterId = 1L;

        Student studentByIdCharacter = studentRepository.findStudentByIdCharacter(characterId);

        assertThat(studentByIdCharacter.getId()).isEqualTo(1);
    }*/

    @Order(6)
    @Test
    void shouldDeleteStudent() {
    }

}
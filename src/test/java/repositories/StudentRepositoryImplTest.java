package repositories;

import entities.HPCharacter;
import entities.House;
import entities.Student;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryImplTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private StudentRepository studentRepository;
    private CharacterRepository characterRepository;
    private static Long studentId;

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        studentRepository = new StudentRepositoryImpl(em);
        characterRepository = new CharacterRepositoryImpl(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
    }

    @Order(1)
    @Test
    void shouldAddStudent() {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter draco = new HPCharacter("Draco", "Malfoy", LocalDate.of(1980, 6, 5));
        Student dracoMalfoy = new Student(draco, 1, House.SLYTHERIN);


        transaction.begin();
        characterRepository.create(draco);
        Student addedStudent = studentRepository.add(dracoMalfoy);
        transaction.commit();
        studentId = dracoMalfoy.getId();

        assertThat(addedStudent.getId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    void shouldFindStudentById() {
        EntityTransaction transaction = em.getTransaction();

        Student foundStudent = studentRepository.findById(studentId);

        assertThat(foundStudent).isNotNull();
    }

    @Order(3)
    @Test
    void shouldUpdateStudentHouse() {
        EntityTransaction transaction = em.getTransaction();
        Student studentToUpdate = studentRepository.findById(studentId);
        studentToUpdate.setHouse(House.GRYFFINDOR);

        transaction.begin();
        studentRepository.update(studentToUpdate);
        transaction.commit();
        Student studentAfterUpdate = studentRepository.findById(studentId);

        assertThat(studentAfterUpdate.getHouse()).isEqualTo(House.GRYFFINDOR);
    }

    @Order(4)
    @Test
    void shouldReturnAllStudentsFromChosenHouse() {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter harryPotter = new HPCharacter("Harry", "Potter", LocalDate.of(1980, 7, 31));
        HPCharacter ronWeasley = new HPCharacter("Ronald", "Weasley", LocalDate.of(1980, 5, 23));
        HPCharacter hermioneGranger = new HPCharacter("Hermione", "Granger", LocalDate.of(1980, 2, 12));
        Student harryStudent = new Student(harryPotter, 1, House.GRYFFINDOR);
        Student ronStudent = new Student(ronWeasley, 1, House.GRYFFINDOR);
        Student hermioneStudent = new Student(hermioneGranger, 1, House.GRYFFINDOR);
        House gryffindor = House.GRYFFINDOR;

        transaction.begin();
        characterRepository.create(harryPotter);
        characterRepository.create(ronWeasley);
        characterRepository.create(hermioneGranger);
        studentRepository.add(harryStudent);
        studentRepository.add(ronStudent);
        studentRepository.add(hermioneStudent);
        transaction.commit();
        List<Student> allStudentFromHouse = studentRepository.findAllStudentsFromHouse(gryffindor);

        assertThat(allStudentFromHouse.size()).isEqualTo(4);
    }

    @Order(5)
    @Test
    void shouldFindAllStudentsWithGivenLastName() {

        List<Student> studentList = studentRepository.findStudentsByLastName("Potter");

        assertThat(studentList.size()).isEqualTo(1);
    }

    @Order(6)
    @Test
    void shouldDeleteStudent() {
        EntityTransaction transaction = em.getTransaction();
        Student studentToDelete = studentRepository.findById(studentId);

        transaction.begin();
        studentRepository.delete(studentToDelete);
        transaction.commit();
        Student studentAfterDelete = studentRepository.findById(studentId);

        assertThat(studentAfterDelete).isNull();
    }


}
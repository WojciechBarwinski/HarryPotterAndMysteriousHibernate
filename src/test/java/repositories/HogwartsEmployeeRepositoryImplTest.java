package repositories;

import entities.HPCharacter;
import entities.HogwartsEmployee;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HogwartsEmployeeRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private CharacterRepository characterRepository;
    private HogwartsEmployeeRepository hogwartsEmployeeRepository;
    private HPCharacter albusDumbledore;
    private HPCharacter severusSnape;


    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        hogwartsEmployeeRepository = new HogwartsEmployeeRepositoryImpl(em);
        albusDumbledore = new HPCharacter("Albus", "Dumbledore", LocalDate.of(1881, 6, 18));
        severusSnape = new HPCharacter("Severus", "Snape", LocalDate.of(1960, 6, 1));
    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }

    @Order(1)
    @Test
    public void shouldAddHogwartsEmployee() {
        EntityTransaction transaction = em.getTransaction();
        HogwartsEmployee director = new HogwartsEmployee(albusDumbledore, BigDecimal.valueOf(10000), "Director of Hogwart");

        transaction.begin();
        characterRepository.create(albusDumbledore);
        HogwartsEmployee savedDirector = hogwartsEmployeeRepository.create(director);
        transaction.commit();

        assertThat(savedDirector.getId()).isGreaterThan(0);

    }

    @Order(2)
    @Test
    public void shouldAddSecondHogwartsEmployee() {
        EntityTransaction transaction = em.getTransaction();
        HogwartsEmployee elixirMaster = new HogwartsEmployee(severusSnape, BigDecimal.valueOf(6500), "Elixir Master");

        transaction.begin();
        characterRepository.create(severusSnape);
        HogwartsEmployee savedElixirMaster = hogwartsEmployeeRepository.create(elixirMaster);
        transaction.commit();

        assertThat(savedElixirMaster.getId()).isEqualTo(2);
    }

    @Order(3)
    @Test
    public void shouldReturnHogwartsEmployeeById() {
        Long id = 2L;

        HogwartsEmployee foundHogwartsEmployee = hogwartsEmployeeRepository.readById(id);

        assertThat(foundHogwartsEmployee.getId()).isEqualTo(2L);
    }

    @Order(4)
    @Test
    public void shouldUpDateHogwartsEmployee() {
        EntityTransaction transaction = em.getTransaction();
        HogwartsEmployee employeeToUpDate = hogwartsEmployeeRepository.readById(1L);
        BigDecimal newSalary = BigDecimal.valueOf(15000);

        em.detach(employeeToUpDate);
        employeeToUpDate.setSalary(newSalary);
        transaction.begin();
        HogwartsEmployee employeeAfterUpDate = hogwartsEmployeeRepository.upDate(employeeToUpDate);
        transaction.commit();
        em.close();

        assertThat(employeeAfterUpDate.getSalary()).isEqualTo(newSalary);

    }

    @Order(5)
    @Test
    public void shouldDeleteEmployeeFromDB(){
        EntityTransaction transaction = em.getTransaction();
        HogwartsEmployee employeeToDelete = hogwartsEmployeeRepository.readById(2L);

        transaction.begin();
        hogwartsEmployeeRepository.delete(employeeToDelete);
        transaction.commit();

        HogwartsEmployee deletedEmployee = hogwartsEmployeeRepository.readById(2L);
        assertThat(deletedEmployee).isNull();
    }

    @Order(6)
    @Test
    public void shouldDeleteEmployeeFromDBbyId(){
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        hogwartsEmployeeRepository.deleteById(1L);
        transaction.commit();

        HogwartsEmployee deletedEmployee = hogwartsEmployeeRepository.readById(1L);
        assertThat(deletedEmployee).isNull();
    }
}


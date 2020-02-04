package repositories;

import entities.HPCharacter;
import entities.HogwartsEmployee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HogwartsEmployeeRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private CharacterRepository characterRepository;
    private HogwartsEmployeeRepository hogwartsEmployeeRepository;
    private HPCharacter albusDumbledore;
    private HPCharacter harryPotter;
    private HPCharacter severusSnape;


    @BeforeEach
    public void setUp(){
        em = emf.createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        hogwartsEmployeeRepository = new HogwartsEmployeeRepositoryImpl(em);
        albusDumbledore = new HPCharacter("Albus", "Dumbledore", LocalDate.of(1881, 6, 18));
        harryPotter = new HPCharacter("Harry", "Potter", LocalDate.of(1980, 7, 31));
        severusSnape = new HPCharacter("Severus", "Snape", LocalDate.of(1960, 6, 1));
    }

    @AfterEach
    public void cleanUp(){
        em.close();
    }

    @Test
    @Order(1)
    public void shouldAddHogwartsEmployee(){
        EntityTransaction transaction = em.getTransaction();
        HogwartsEmployee director = new HogwartsEmployee(albusDumbledore, BigDecimal.valueOf(10000), "Director of Hogwart");

        transaction.begin();
        characterRepository.create(albusDumbledore);
        HogwartsEmployee savedDirector = hogwartsEmployeeRepository.create(director);
        transaction.commit();

        assertThat(savedDirector.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void shouldAddSecondHogwartsEmployee(){
        EntityTransaction transaction = em.getTransaction();
        HogwartsEmployee elixirMaster = new HogwartsEmployee(severusSnape, BigDecimal.valueOf(6500), "Elixir Master");

        transaction.begin();
        characterRepository.create(severusSnape);
        HogwartsEmployee savedElixirMaster = hogwartsEmployeeRepository.create(elixirMaster);
        transaction.commit();
        assertThat(savedElixirMaster.getId()).isEqualTo(2);
    }
}
package repositories;

import entities.HPCharacter;
import entities.HogwartsEmployee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp(){
        em = emf.createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        hogwartsEmployeeRepository = new HogwartsEmployeeRepositoryImpl(em);
    }

    @AfterEach
    public void cleanUp(){
        em.close();
    }

    @Test
    public void shouldAddHogwartsEmployee(){
        EntityTransaction transaction = em.getTransaction();
        LocalDate birthDate = LocalDate.of(1881, 6, 18);
        HPCharacter albusDumbledore = new HPCharacter("Albus", "Dumbledore", birthDate);
        HogwartsEmployee director = new HogwartsEmployee(albusDumbledore, BigDecimal.valueOf(10000), "Director of Hogwart");

        transaction.begin();
        HPCharacter savedAlbusDumbledore = characterRepository.create(albusDumbledore);
        HogwartsEmployee savedDirector = hogwartsEmployeeRepository.create(director);
        transaction.commit();

        assertThat(savedDirector.getId()).isGreaterThan(0);

    }

}
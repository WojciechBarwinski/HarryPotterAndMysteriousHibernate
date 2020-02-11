package harryPotterApp.startingData;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.HogwartsJobRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class DataInitializer {

    static CharacterRepository characterRepository;
    static HPCharacter harryPotter = new HPCharacter("Harry", "Potter", LocalDate.of(1980, 7, 31));
    static HPCharacter albusDumbledore = new HPCharacter("Albus", "Dumbledore", LocalDate.of(1881, 6, 18));
    static HPCharacter severusSnape = new HPCharacter("Severus", "Snape", LocalDate.of(1960, 6, 1));
    static HPCharacter ronWeasley = new HPCharacter("Ron", "Weasley", LocalDate.of(1980, 8, 15));
    static HPCharacter hagrid = new HPCharacter("Rubeus", "Hagrid", LocalDate.of(1960, 6, 1));

    static HogwartsJobRepository hogwartsJobRepository;
    private DataInitializer(){}

    public static void addAllData(EntityManager em){
        characterRepository = new CharacterRepositoryImpl(em);
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        characterRepository.add(harryPotter);
        characterRepository.add(albusDumbledore);
        characterRepository.add(severusSnape);
        characterRepository.add(ronWeasley);
        characterRepository.add(hagrid);
        transaction.commit();
    }

}

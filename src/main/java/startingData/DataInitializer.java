package startingData;

import entities.HPCharacter;
import entities.HPLocation;
import entities.HogwartsJob;
import entities.Pet;
import repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DataInitializer {
    private DataInitializer(){}


    static private CharacterRepository characterRepository;
    static private HogwartsJobRepository hogwartsJobRepository;
    static private PetRepository petRepository;
    static private HPLocationRepository locationRepository;
    static private StudentRepository studentRepository;
    static private ItemRepository itemRepository;

    static private HPCharacter harryPotter = new HPCharacter("Harry", "Potter", LocalDate.of(1980, 7, 31));
    static private HPCharacter albusDumbledore = new HPCharacter("Albus", "Dumbledore", LocalDate.of(1881, 6, 18));
    static private HPCharacter severusSnape = new HPCharacter("Severus", "Snape", LocalDate.of(1960, 6, 1));
    static private HPCharacter ronWeasley = new HPCharacter("Ron", "Weasley", LocalDate.of(1980, 8, 15));
    static private HPCharacter hagrid = new HPCharacter("Rubeus", "Hagrid", LocalDate.of(1960, 6, 1));
    static private HPCharacter minerwaMcGonagall = new HPCharacter("Minerwa", "McGonagall", LocalDate.of(1935, 10, 4));

    static private HogwartsJob teacher = new HogwartsJob(BigDecimal.valueOf(5000), "Teacher");
    static private HogwartsJob headmaster = new HogwartsJob(BigDecimal.valueOf(20000), "Director of Hogwarts");
    static private HogwartsJob houseHead = new HogwartsJob(BigDecimal.valueOf(7500), "House Head");
    static private HogwartsJob assistant = new HogwartsJob(BigDecimal.valueOf(3000), "Caretaker");

    static private HPLocation hogwarts = new HPLocation("Hogwarts");
    static private HPLocation hagridsHouse = new HPLocation("Hagrid's cottage");
    static private HPLocation malfoyManor = new HPLocation("Malfoy Manor");
    static private HPLocation shriekingShack = new HPLocation("Shrieking Shack");



    public static void addAllData(EntityManager em){
        EntityTransaction transaction = em.getTransaction();
        characterRepository = new CharacterRepositoryImpl(em);
        hogwartsJobRepository = new HogwartsJobRepositoryImpl(em);
        petRepository = new PetRepositoryImpl(em);
        locationRepository = new HPLocationRepositoryImpl(em);
        studentRepository = new StudentRepositoryImpl(em);
        itemRepository = new ItemRepositoryImpl(em);


        transaction.begin();

        characterRepository.add(harryPotter);
        characterRepository.add(albusDumbledore);
        characterRepository.add(severusSnape);
        characterRepository.add(ronWeasley);
        characterRepository.add(hagrid);
        characterRepository.add(minerwaMcGonagall);

        hogwartsJobRepository.add(teacher);
        hogwartsJobRepository.add(headmaster);
        hogwartsJobRepository.add(houseHead);
        hogwartsJobRepository.add(assistant);

        petRepository.add(new Pet("Hedwig", characterRepository.findById(1L), "Owl"));
        petRepository.add(new Pet("Fawkes", characterRepository.findById(2L), "Phoenix"));
        petRepository.add(new Pet("Helga III", characterRepository.findById(4L), "Owl"));
        petRepository.add(new Pet("Max", characterRepository.findById(6L), "Cat"));
        petRepository.add(new Pet("Fang", characterRepository.findById(5L), "Dog"));

        locationRepository.addLocation(hogwarts);
        locationRepository.addLocation(hagridsHouse);
        locationRepository.addLocation(malfoyManor);
        locationRepository.addLocation(shriekingShack);

        hogwartsJobRepository.findById(1L).setCharactersAsEmployee(characterRepository.findById(3L));
        hogwartsJobRepository.findById(1L).setCharactersAsEmployee(characterRepository.findById(6L));
        hogwartsJobRepository.findById(3L).setCharactersAsEmployee(characterRepository.findById(3L));
        hogwartsJobRepository.findById(3L).setCharactersAsEmployee(characterRepository.findById(6L));
        hogwartsJobRepository.findById(4L).setCharactersAsEmployee(characterRepository.findById(5L));
        hogwartsJobRepository.findById(2L).setCharactersAsEmployee(characterRepository.findById(2L));
        hogwartsJobRepository.updateById(1L);
        hogwartsJobRepository.updateById(3L);
        hogwartsJobRepository.updateById(4L);

        characterRepository.findById(1L).setLocation(locationRepository.findLocationById(4L));
        characterRepository.findById(2L).setLocation(locationRepository.findLocationById(1L));
        characterRepository.findById(3L).setLocation(locationRepository.findLocationById(1L));
        characterRepository.findById(4L).setLocation(locationRepository.findLocationById(4L));
        characterRepository.findById(5L).setLocation(locationRepository.findLocationById(2L));
        characterRepository.findById(6L).setLocation(locationRepository.findLocationById(1L));
        locationRepository.updateLocationById(1L);
        locationRepository.updateLocationById(2L);
        locationRepository.updateLocationById(3L);
        locationRepository.updateLocationById(4L);
        transaction.commit();
    }
}

package harryPotterApp.startingData;


import harryPotterApp.entities.*;
import harryPotterApp.repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DataInitializer {
    private DataInitializer() {
    }


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
    static private HPCharacter georgeWeasley = new HPCharacter("George", "Weasley", LocalDate.of(1977, 10, 4));
    static private HPCharacter dracoMalfoy = new HPCharacter("Draco", "Malfoy", LocalDate.of(1980, 10, 4));

    static private HogwartsJob teacher = new HogwartsJob(BigDecimal.valueOf(5000), "Teacher");
    static private HogwartsJob headmaster = new HogwartsJob(BigDecimal.valueOf(20000), "Director of Hogwarts");
    static private HogwartsJob houseHead = new HogwartsJob(BigDecimal.valueOf(7500), "House Head");
    static private HogwartsJob assistant = new HogwartsJob(BigDecimal.valueOf(3000), "Caretaker");

    static private HPLocation hogwarts = new HPLocation("Hogwarts");
    static private HPLocation hagridsHouse = new HPLocation("Hagrid's cottage");
    static private HPLocation malfoyManor = new HPLocation("Malfoy Manor");
    static private HPLocation shriekingShack = new HPLocation("Shrieking Shack");


    public static void addAllData(EntityManager em) {

        characterRepository = new CharacterRepositoryImpl(em);
        hogwartsJobRepository = new HogwartsJobRepositoryImpl(em);
        petRepository = new PetRepositoryImpl(em);
        locationRepository = new HPLocationRepositoryImpl(em);
        studentRepository = new StudentRepositoryImpl(em);
        itemRepository = new ItemRepositoryImpl(em);

        if (characterRepository.getAllCharacters().isEmpty()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            //Dane do heroku
            characterRepository.add(harryPotter);
            characterRepository.add(albusDumbledore);
            characterRepository.add(severusSnape);
            characterRepository.add(ronWeasley);
            characterRepository.add(hagrid);
            characterRepository.add(minerwaMcGonagall);
            characterRepository.add(georgeWeasley);
            characterRepository.add(dracoMalfoy);

            characterRepository.findById(2L).setImagePath("https://r-scale-60.dcs.redcdn.pl/scale/o2/tvn/web-content/m/p1/i/3e313b9badf12632cdae5452d20e1af6/f5d73034-6af1-11e3-9197-0025b511229e.jpg?type=1&srcmode=0&srcx=1/1&srcy=1/1&srcw=1/1&srch=1/1&dstw=970&dsth=724&quality=80");
            characterRepository.findById(12L).setImagePath("https://static.independent.co.uk/s3fs-public/thumbnails/image/2014/07/24/17/Albus-Dumbledore.jpg");
            characterRepository.findById(22L).setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/5/58/Severus-Snape-severus-snape-1972318-359-481.jpeg/revision/latest?cb=20131225182034&path-prefix=pl");
            characterRepository.findById(32L).setImagePath("https://samequizy.pl/wp-content/uploads/2017/08/filing_images_0c7c5b9e6db5.jpg");
            characterRepository.findById(42L).setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/f/ff/HAGRID_RUBEUS_HAGRID.jpg/revision/latest/top-crop/width/360/height/450?cb=20110517140811&path-prefix=pl");
            characterRepository.findById(52L).setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/6/6a/McGonagallaward.png/revision/latest?cb=20161112125656&path-prefix=pl");
            characterRepository.findById(72L).setImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRKBpDDBss_bUrrGof_skULm5i62aZQ9jJHmAICMg5aLsfFZxzQ");

            hogwartsJobRepository.add(teacher);
            hogwartsJobRepository.add(headmaster);
            hogwartsJobRepository.add(houseHead);
            hogwartsJobRepository.add(assistant);

            petRepository.add(new Pet("Hedwig", characterRepository.findById(2L), "Owl"));
            petRepository.add(new Pet("Fawkes", characterRepository.findById(12L), "Phoenix"));
            petRepository.add(new Pet("Helga III", characterRepository.findById(32L), "Owl"));
            petRepository.add(new Pet("Max", characterRepository.findById(52L), "Cat"));
            petRepository.add(new Pet("Fang", characterRepository.findById(42L), "Dog"));

            hogwarts.setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/b/bd/Dhogwarts.jpg");
            hagridsHouse.setImagePath("https://www.shemazing.net/wp-content/uploads/2019/04/1556285270_hagrid-656x438.png");
            malfoyManor.setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/a/ae/Malfoy_Manor_Pottermore.png");
            shriekingShack.setImagePath("https://i.pinimg.com/originals/a2/b4/21/a2b42165e1b9acc82be0982064758b7f.jpg");
            locationRepository.add(hogwarts);
            locationRepository.add(hagridsHouse);
            locationRepository.add(malfoyManor);
            locationRepository.add(shriekingShack);

            hogwartsJobRepository.findById(2L).setCharactersAsEmployee(characterRepository.findById(22L));
            hogwartsJobRepository.findById(2L).setCharactersAsEmployee(characterRepository.findById(52L));
            hogwartsJobRepository.findById(22L).setCharactersAsEmployee(characterRepository.findById(22L));
            hogwartsJobRepository.findById(22L).setCharactersAsEmployee(characterRepository.findById(52L));
            hogwartsJobRepository.findById(32L).setCharactersAsEmployee(characterRepository.findById(42L));
            hogwartsJobRepository.findById(12L).setCharactersAsEmployee(characterRepository.findById(12L));
            hogwartsJobRepository.updateById(2L);
            hogwartsJobRepository.updateById(22L);
            hogwartsJobRepository.updateById(32L);
            hogwartsJobRepository.updateById(12L);

            characterRepository.findById(2L).setLocation(locationRepository.findById(32L));
            characterRepository.findById(12L).setLocation(locationRepository.findById(2L));
            characterRepository.findById(22L).setLocation(locationRepository.findById(2L));
            characterRepository.findById(32L).setLocation(locationRepository.findById(32L));
            characterRepository.findById(42L).setLocation(locationRepository.findById(12L));
            characterRepository.findById(52L).setLocation(locationRepository.findById(2L));
            locationRepository.updateById(2L);
            locationRepository.updateById(12L);
            locationRepository.updateById(22L);
            locationRepository.updateById(32L);

            studentRepository.add(new Student(characterRepository.findById(2L), 1, House.GRYFFINDOR));
            studentRepository.add(new Student(characterRepository.findById(32L), 1, House.GRYFFINDOR));
            studentRepository.add(new Student(characterRepository.findById(62L), 4, House.GRYFFINDOR));
            studentRepository.add(new Student(characterRepository.findById(72L), 1, House.SLYTHERIN));

            itemRepository.add(new Item(ItemType.BROOMSTICK, "Nimbus 2000", BigDecimal.valueOf(10000)));
            itemRepository.add(new Item(ItemType.BROOMSTICK, "Nimbus 2001", BigDecimal.valueOf(15000)));
            itemRepository.add(new Item(ItemType.WAND, "Black Wand", BigDecimal.valueOf(100000)));
            itemRepository.add(new Item(ItemType.ROBE, "Invisible Cloak", BigDecimal.valueOf(18500)));
            itemRepository.add(new Item(ItemType.WEAPON, "Sword of Gryffindor", BigDecimal.valueOf(50000)));

            itemRepository.findById(12L).setItemOwners(characterRepository.findById(2L));
            itemRepository.findById(12L).setItemOwners(characterRepository.findById(72L));
            itemRepository.findById(42L).setItemOwners(characterRepository.findById(2L));
            itemRepository.findById(32L).setItemOwners(characterRepository.findById(2L));
            itemRepository.findById(2L).setItemOwners(characterRepository.findById(32L));
            itemRepository.findById(2L).setItemOwners(characterRepository.findById(62L));

            /* DANE do servera lokalnego
            * characterRepository.add(harryPotter);
            characterRepository.add(albusDumbledore);
            characterRepository.add(severusSnape);
            characterRepository.add(ronWeasley);
            characterRepository.add(hagrid);
            characterRepository.add(minerwaMcGonagall);
            characterRepository.add(georgeWeasley);
            characterRepository.add(dracoMalfoy);

            characterRepository.findById(1L).setImagePath("https://r-scale-60.dcs.redcdn.pl/scale/o2/tvn/web-content/m/p1/i/3e313b9badf12632cdae5452d20e1af6/f5d73034-6af1-11e3-9197-0025b511229e.jpg?type=1&srcmode=0&srcx=1/1&srcy=1/1&srcw=1/1&srch=1/1&dstw=970&dsth=724&quality=80");
            characterRepository.findById(2L).setImagePath("https://static.independent.co.uk/s3fs-public/thumbnails/image/2014/07/24/17/Albus-Dumbledore.jpg");
            characterRepository.findById(3L).setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/5/58/Severus-Snape-severus-snape-1972318-359-481.jpeg/revision/latest?cb=20131225182034&path-prefix=pl");
            characterRepository.findById(4L).setImagePath("https://samequizy.pl/wp-content/uploads/2017/08/filing_images_0c7c5b9e6db5.jpg");
            characterRepository.findById(5L).setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/f/ff/HAGRID_RUBEUS_HAGRID.jpg/revision/latest/top-crop/width/360/height/450?cb=20110517140811&path-prefix=pl");
            characterRepository.findById(6L).setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/6/6a/McGonagallaward.png/revision/latest?cb=20161112125656&path-prefix=pl");
            characterRepository.findById(8L).setImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRKBpDDBss_bUrrGof_skULm5i62aZQ9jJHmAICMg5aLsfFZxzQ");

            hogwartsJobRepository.add(teacher);
            hogwartsJobRepository.add(headmaster);
            hogwartsJobRepository.add(houseHead);
            hogwartsJobRepository.add(assistant);

            petRepository.add(new Pet("Hedwig", characterRepository.findById(1L), "Owl"));
            petRepository.add(new Pet("Fawkes", characterRepository.findById(2L), "Phoenix"));
            petRepository.add(new Pet("Helga III", characterRepository.findById(4L), "Owl"));
            petRepository.add(new Pet("Max", characterRepository.findById(6L), "Cat"));
            petRepository.add(new Pet("Fang", characterRepository.findById(5L), "Dog"));

            hogwarts.setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/b/bd/Dhogwarts.jpg");
            hagridsHouse.setImagePath("https://www.shemazing.net/wp-content/uploads/2019/04/1556285270_hagrid-656x438.png");
            malfoyManor.setImagePath("https://vignette.wikia.nocookie.net/harrypotter/images/a/ae/Malfoy_Manor_Pottermore.png");
            shriekingShack.setImagePath("https://i.pinimg.com/originals/a2/b4/21/a2b42165e1b9acc82be0982064758b7f.jpg");
            locationRepository.add(hogwarts);
            locationRepository.add(hagridsHouse);
            locationRepository.add(malfoyManor);
            locationRepository.add(shriekingShack);

            hogwartsJobRepository.findById(1L).setCharactersAsEmployee(characterRepository.findById(3L));
            hogwartsJobRepository.findById(1L).setCharactersAsEmployee(characterRepository.findById(6L));
            hogwartsJobRepository.findById(3L).setCharactersAsEmployee(characterRepository.findById(3L));
            hogwartsJobRepository.findById(3L).setCharactersAsEmployee(characterRepository.findById(6L));
            hogwartsJobRepository.findById(4L).setCharactersAsEmployee(characterRepository.findById(5L));
            hogwartsJobRepository.findById(2L).setCharactersAsEmployee(characterRepository.findById(2L));
            hogwartsJobRepository.updateById(1L);
            hogwartsJobRepository.updateById(3L);
            hogwartsJobRepository.updateById(4L);

            characterRepository.findById(1L).setLocation(locationRepository.findById(4L));
            characterRepository.findById(2L).setLocation(locationRepository.findById(1L));
            characterRepository.findById(3L).setLocation(locationRepository.findById(1L));
            characterRepository.findById(4L).setLocation(locationRepository.findById(4L));
            characterRepository.findById(5L).setLocation(locationRepository.findById(2L));
            characterRepository.findById(6L).setLocation(locationRepository.findById(1L));
            locationRepository.updateById(1L);
            locationRepository.updateById(2L);
            locationRepository.updateById(3L);
            locationRepository.updateById(4L);

            studentRepository.add(new Student(characterRepository.findById(1L), 1, House.GRYFFINDOR));
            studentRepository.add(new Student(characterRepository.findById(4L), 1, House.GRYFFINDOR));
            studentRepository.add(new Student(characterRepository.findById(7L), 4, House.GRYFFINDOR));
            studentRepository.add(new Student(characterRepository.findById(8L), 1, House.SLYTHERIN));

            itemRepository.add(new Item(ItemType.BROOMSTICK, "Nimbus 2000", BigDecimal.valueOf(10000)));
            itemRepository.add(new Item(ItemType.BROOMSTICK, "Nimbus 2001", BigDecimal.valueOf(15000)));
            itemRepository.add(new Item(ItemType.WAND, "Black Wand", BigDecimal.valueOf(100000)));
            itemRepository.add(new Item(ItemType.ROBE, "Invisible Cloak", BigDecimal.valueOf(18500)));
            itemRepository.add(new Item(ItemType.WEAPON, "Sword of Gryffindor", BigDecimal.valueOf(50000)));

            itemRepository.findById(2L).setItemOwners(characterRepository.findById(1L));
            itemRepository.findById(2L).setItemOwners(characterRepository.findById(8L));
            itemRepository.findById(5L).setItemOwners(characterRepository.findById(1L));
            itemRepository.findById(4L).setItemOwners(characterRepository.findById(1L));
            itemRepository.findById(1L).setItemOwners(characterRepository.findById(4L));
            itemRepository.findById(1L).setItemOwners(characterRepository.findById(7L));*/
            transaction.commit();
        }

    }
}

package repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.Item;
import harryPotterApp.entities.ItemType;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.ItemRepository;
import harryPotterApp.repositories.ItemRepositoryImpl;
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
class ItemRepositoryImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em;
    private CharacterRepository characterRepository;
    private ItemRepository itemRepository;
    private HPCharacter albusDumbledore;
    private HPCharacter severusSnape;
    private HPCharacter harryPotter;

    private Item nimbus2000;
    private Item blackWand;
    private Item invisibleCloak;

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        characterRepository = new CharacterRepositoryImpl(em);
        itemRepository = new ItemRepositoryImpl(em);
        albusDumbledore = new HPCharacter("Albus", "Dumbledore", LocalDate.of(1881, 6, 18));
        severusSnape = new HPCharacter("Severus", "Snape", LocalDate.of(1960, 6, 1));
        harryPotter = new HPCharacter("Harry", "Potter", LocalDate.of(1980, 7, 31));

        nimbus2000 = new Item(ItemType.BROOMSTICK, "Nimbus 2000", BigDecimal.valueOf(4000));
        blackWand = new Item(ItemType.WAND, "Black Wand", BigDecimal.valueOf(150000));
        invisibleCloak = new Item(ItemType.ROBE, "Invisible Cloak", BigDecimal.valueOf(25000));
    }

    @AfterEach
    public void cleanUp() {
        em.close();
    }

    @Order(1)
    @Test
    public void shouldAddThreeHPCharacterToDataBase() {
        EntityTransaction transaction = em.getTransaction();
        List<HPCharacter> addedHPCharacters = new ArrayList<>();

        transaction.begin();
        addedHPCharacters.add(characterRepository.add(albusDumbledore));
        addedHPCharacters.add(characterRepository.add(severusSnape));
        addedHPCharacters.add(characterRepository.add(harryPotter));
        transaction.commit();

        HPCharacter hpCharacter1 = addedHPCharacters.get(0);
        HPCharacter hpCharacter2 = addedHPCharacters.get(1);
        HPCharacter hpCharacter3 = addedHPCharacters.get(2);

        assertThat(addedHPCharacters.size()).isEqualTo(3);
        assertThat(hpCharacter1.getId()).isGreaterThan(0);
        assertThat(hpCharacter2.getId()).isGreaterThan(0);
        assertThat(hpCharacter3.getId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    void shouldAddNewItemsToDataBase() {
        EntityTransaction transaction = em.getTransaction();
        List<Item> addedItem = new ArrayList<>();
        transaction.begin();
        addedItem.add(itemRepository.add(nimbus2000));
        addedItem.add(itemRepository.add(blackWand));
        addedItem.add(itemRepository.add(invisibleCloak));
        transaction.commit();

        Item item1 = addedItem.get(0);
        Item item2 = addedItem.get(1);
        Item item3 = addedItem.get(2);

        assertThat(item1.getId()).isGreaterThan(0);
        assertThat(item2.getId()).isGreaterThan(0);
        assertThat(item3.getId()).isGreaterThan(0);
    }

    @Order(3)
    @Test
    void shouldLinkBetweenItemsAndCharacters() {
        EntityTransaction transaction = em.getTransaction();
        Item itemToLink1 = itemRepository.findById(1L);
        Item itemToLink2 = itemRepository.findById(2L);
        Item itemToLink3 = itemRepository.findById(3L);
        HPCharacter hpCharacter1 = characterRepository.findById(1L);
        HPCharacter hpCharacter2 = characterRepository.findById(2L);
        HPCharacter hpCharacter3 = characterRepository.findById(3L);
        itemToLink1.setItemOwners(hpCharacter3);
        itemToLink2.setItemOwners(hpCharacter1);
        itemToLink2.setItemOwners(hpCharacter3);
        itemToLink3.setItemOwners(hpCharacter2);

        transaction.begin();
        itemRepository.updateById(1L);
        itemRepository.updateById(2L);
        itemRepository.updateById(3L);
        characterRepository.updateById(1L);
        characterRepository.updateById(2L);
        characterRepository.updateById(3L);
        transaction.commit();
        Item itemUpdated = itemRepository.findById(2L);
        HPCharacter characterUpdated = characterRepository.findById(3L);

        assertThat(itemUpdated.getItemOwners().size()).isEqualTo(2);
        assertThat(characterUpdated.getItems().size()).isEqualTo(2);
    }

    @Order(10)
    @Test
    void shouldDeleteOneItem(){
        EntityTransaction transaction = em.getTransaction();
        Long idItemToDeleted = 1L;

        transaction.begin();
        itemRepository.deleteById(idItemToDeleted);
        transaction.commit();

        Item deletedItem = itemRepository.findById(idItemToDeleted);
        assertThat(deletedItem).isNull();
    }
}
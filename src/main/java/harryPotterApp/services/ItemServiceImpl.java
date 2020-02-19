package harryPotterApp.services;

import harryPotterApp.entities.Item;
import harryPotterApp.entities.ItemType;
import harryPotterApp.repositories.ItemRepository;
import harryPotterApp.repositories.ItemRepositoryImpl;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ItemServiceImpl implements ItemService {
    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private ItemRepository itemRepository = new ItemRepositoryImpl(em);

    @Override
    public List<Item> getAllItemsByType(String type) {
        em.clear();
        switch (type) {
            case "WAND":
                return itemRepository.getAllItemsByType(ItemType.WAND);
            case "WEAPON":
                return itemRepository.getAllItemsByType(ItemType.WEAPON);
            case "BROOMSTICK":
                return itemRepository.getAllItemsByType(ItemType.BROOMSTICK);
            case "ROBE":
                return itemRepository.getAllItemsByType(ItemType.ROBE);
        }
        return Collections.emptyList();
    }

    @Override
    public void add(String name, String value, String type) {
        long itemValue = Long.parseLong(value);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        switch (type) {
            case "WAND":
                itemRepository.add(new Item(ItemType.WAND, name, BigDecimal.valueOf(itemValue)));
                break;
            case "WEAPON":
                itemRepository.add(new Item(ItemType.WEAPON, name, BigDecimal.valueOf(itemValue)));
                break;
            case "BROOMSTICK":
                itemRepository.add(new Item(ItemType.BROOMSTICK, name, BigDecimal.valueOf(itemValue)));
                break;
            case "ROBE":
                itemRepository.add(new Item(ItemType.ROBE, name, BigDecimal.valueOf(itemValue)));
                break;
        }
        transaction.commit();
    }

    @Override
    public Item findById(String id) {
        return itemRepository.findById(Long.valueOf(id));
    }

    @Override
    public void deleteById(String itemId) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        itemRepository.deleteById(Long.parseLong(itemId));
        transaction.commit();
    }

    @Override
    public List<Item> getAllItemsByCharacterId(String id) {
        return itemRepository.getItemByOwnerID(Long.parseLong(id));
    }

    @Override
    public List<Item> getAllItemsWhichAreNotOwnedByCharacter(String id) {
        List<Item> allItems = itemRepository.getAllItems();
        List<Item> charactersItems = itemRepository.getItemByOwnerID(Long.valueOf(id));
        List<Item> resultList = new ArrayList<>(allItems);
        resultList.removeAll(charactersItems);
        return resultList;
    }
}

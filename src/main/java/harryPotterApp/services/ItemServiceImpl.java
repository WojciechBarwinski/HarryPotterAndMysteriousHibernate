package harryPotterApp.services;

import harryPotterApp.entities.Item;
import harryPotterApp.entities.ItemType;
import harryPotterApp.repositories.ItemRepository;
import harryPotterApp.repositories.ItemRepositoryImpl;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private ItemRepository itemRepository = new ItemRepositoryImpl(em);

    @Override
    public List<Item> getAllItemsByType(String type) {
        switch (type) {
            case "WANDS":
                return itemRepository.getAllItemsByType(ItemType.WAND);
            case "WEAPONS":
                return itemRepository.getAllItemsByType(ItemType.WEAPON);
            case "BROOMSTICKS":
                return itemRepository.getAllItemsByType(ItemType.BROOMSTICK);
            case "ROBES":
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
            case "WANDS":
                itemRepository.add(new Item(ItemType.WAND, name, BigDecimal.valueOf(itemValue)));
                break;
            case "WEAPONS":
                itemRepository.add(new Item(ItemType.WEAPON, name, BigDecimal.valueOf(itemValue)));
                break;
            case "BROOMSTICKS":
                itemRepository.add(new Item(ItemType.BROOMSTICK, name, BigDecimal.valueOf(itemValue)));
                break;
            case "ROBES":
                itemRepository.add(new Item(ItemType.ROBE, name, BigDecimal.valueOf(itemValue)));
                break;
        }
        transaction.commit();
    }
}

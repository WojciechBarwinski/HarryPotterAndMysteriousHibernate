package harryPotterApp.services;

import harryPotterApp.entities.Item;
import harryPotterApp.entities.ItemType;
import harryPotterApp.repositories.ItemRepository;
import harryPotterApp.repositories.ItemRepositoryImpl;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
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
}

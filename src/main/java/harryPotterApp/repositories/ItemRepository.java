package harryPotterApp.repositories;

import harryPotterApp.entities.Item;
import harryPotterApp.entities.ItemType;

import java.util.List;
import java.util.Set;

public interface ItemRepository {

    Item add(Item item);
    Item updateById(Long id);
    Item findById(Long id);
    void deleteById(Long id);
    List<Item> getItemByOwnerID(Long id);
    List<Item> getAllItemsByType(ItemType type);
    List<Item> getAllItems();
}

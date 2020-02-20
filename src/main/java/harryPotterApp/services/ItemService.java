package harryPotterApp.services;

import harryPotterApp.entities.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {

    List<Item> getAllItemsByType(String type);

    void add(String name, String value, String type);

    void deleteById(String itemId);

    Item findById(String id);

    Item findByName(String name);

    List<Item> getAllItemsByCharacterId(String id);


    List<Item> getAllItemsWhichAreNotOwnedByCharacter(String id);
}

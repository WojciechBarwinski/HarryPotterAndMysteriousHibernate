package harryPotterApp.services;

import harryPotterApp.entities.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItemsByType(String type);

    void add(String name, String value, String type);

    void deleteById(String itemId);

    Item findById(String id);
}

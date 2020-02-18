package harryPotterApp.services;

import harryPotterApp.entities.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItemsByType(String type);
}

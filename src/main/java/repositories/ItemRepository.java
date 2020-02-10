package repositories;

import entities.Item;

public interface ItemRepository {

    Item add(Item item);
    Item updateById(Long id);
    Item findById(Long id);
    void deleteById(Long id);
}

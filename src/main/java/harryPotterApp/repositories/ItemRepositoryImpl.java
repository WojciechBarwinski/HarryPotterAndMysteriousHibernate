package harryPotterApp.repositories;

import harryPotterApp.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class ItemRepositoryImpl implements ItemRepository {

    private EntityManager em;

    public ItemRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item add(Item item) {
        if (Objects.isNull(item.getId())){
            em.persist(item);
        }
        return item;
    }

    @Override
    public Item updateById(Long id) {
        return em.merge(findById(id));
    }

    @Override
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Item item = findById(id);
        em.remove(item);
    }

    @Override
    public List<Item> getItemByOwnerID(Long id) {
        Query query = em.createQuery("SELECT item FROM Item as item JOIN item.itemOwners as oId where oId.id =:id")
                .setParameter("id", id);
        return query.getResultList();
    }
}

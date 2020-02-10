package repositories;

import entities.Pet;

import javax.persistence.EntityManager;
import java.util.Objects;

public class PetRepositoryImpl implements PetRepository {
    EntityManager em;

    public PetRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Pet add(Pet pet) {
        if (Objects.isNull(pet.getId())) {
            em.persist(pet);
        }
        return pet;
    }

    @Override
    public Pet updateById(Long id) {
        return em.merge(findById(id));
    }

    @Override
    public Pet findById(Long id) {
        return em.find(Pet.class, id);
    }

    @Override
    public void deletedById(Long id) {
        em.remove(findById(id));
    }
}

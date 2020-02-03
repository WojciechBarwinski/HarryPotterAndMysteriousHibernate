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
}

package harryPotterApp.repositories;

import harryPotterApp.entities.Pet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
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

    @Override
    public List<Pet> getAllPets() {
        TypedQuery<Pet> allPets = em.createQuery("SELECT p FROM Pet p", Pet.class);
        List<Pet> allPetsResultList = allPets.getResultList();
        return allPetsResultList;
    }
}

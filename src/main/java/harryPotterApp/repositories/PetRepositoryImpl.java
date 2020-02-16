package harryPotterApp.repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.Pet;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    @Override
    public List<HPCharacter> getAllCharactersWithoutPet() {
        Query query = em.createQuery("SELECT hpc FROM HPCharacter hpc LEFT JOIN Pet as p ON hpc.id=p.owner.id WHERE p.owner.id IS NULL");
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Pet getPetByOwnerId(Long id) {
        Query query = em.createQuery("SELECT pet From Pet as pet where pet.owner.id =:id").setParameter("id", id);
        if (query.getResultList().isEmpty()){
            return null;
        }
        return (Pet) query.getSingleResult();
    }
}

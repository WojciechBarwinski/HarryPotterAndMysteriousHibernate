package harryPotterApp.repositories;

import harryPotterApp.entities.Pet;

public interface PetRepository {
    Pet add(Pet pet);
    Pet updateById(Long id);
    Pet findById(Long id);
    void deletedById(Long id);
}

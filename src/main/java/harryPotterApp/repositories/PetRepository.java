package harryPotterApp.repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.Pet;

import java.util.List;

public interface PetRepository {
    Pet add(Pet pet);
    Pet updateById(Long id);
    Pet findById(Long id);
    void deletedById(Long id);
    List<Pet> getAllPets();
    List<HPCharacter> getAllCharactersWithoutPet();

    Pet getPetByOwnerId(Long id);
}

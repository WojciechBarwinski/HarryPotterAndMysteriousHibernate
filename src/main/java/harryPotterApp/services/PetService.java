package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.dto.PetDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.Pet;

import java.util.List;

public interface PetService {

    List<PetDto> getAllPets();
    List<PetDto> findById (String id);
    void deleteById(Long id);
    List<HPCharacterDto> getAllCharactersWithoutPet();
    void add(String name, String species, String ownerData);
    Long getOwnerIdByFirstAndLastName(String ownerData);
    PetDto preparePetForViewPage(Long id);
}

package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.dto.PetDto;
import harryPotterApp.entities.Pet;

import java.util.List;

public interface PetService {

    List<PetDto> getAllPets();

    List<PetDto> findById (String id);

    List<HPCharacterDto> getAllCharactersWithoutPet();
}

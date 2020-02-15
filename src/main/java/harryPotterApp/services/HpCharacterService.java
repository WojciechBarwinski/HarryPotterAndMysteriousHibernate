package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;

import java.time.LocalDate;
import java.util.List;

public interface HpCharacterService {

    List<HPCharacterDto> getAllCharacters();
    void addCharacter(String firstName, String lastName, String birthDate);
    void deleteCharacterById(Long id);
    List<HPCharacterDto> findCharacterById(String id);
    HPCharacterDto prepareCharacterToView(Long id);
}

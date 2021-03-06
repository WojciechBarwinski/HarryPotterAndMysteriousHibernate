package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;

import java.time.LocalDate;
import java.util.List;

public interface HpCharacterService {

    List<HPCharacterDto> getAllCharacters();
    void addCharacter(String firstName, String lastName, String birthDate);
    void deleteCharacterById(Long id);
    //List<HPCharacterDto> findCharacterById(String id);
    HPCharacter findCharacterById(String id);
    List<HPCharacterDto> findCharacterByFirstNameOrLastName(String name);
    HPCharacterDto prepareCharacterToView(Long id);
    Long getCharacterIdByFirstAndLastName(String characterData);
    //void updateCharacter(String id, String itemToAddId, String itemToRemoveId);
    void addItemToCharacter(String id, Long itemToAddId);
    void removeItemToCharacter(String id, Long itemToRemoveId);
    void updateCharacter(String characterId, String firstName, String lastName, String birthDate);
}

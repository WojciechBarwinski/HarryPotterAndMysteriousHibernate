package harryPotterApp.repositories;

import harryPotterApp.entities.HPCharacter;

import java.util.List;

public interface CharacterRepository {

    HPCharacter add(HPCharacter HPCharacter);
    HPCharacter findById(Long id);
    HPCharacter update(HPCharacter hpCharacter);
    HPCharacter updateById(Long id);
    void delete(HPCharacter characterToDelete);
    void deleteById(Long id);
    List<HPCharacter> getAllCharacters();
}

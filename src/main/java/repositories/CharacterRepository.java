package repositories;

import entities.HPCharacter;

public interface CharacterRepository {

    HPCharacter create(HPCharacter HPCharacter);
    HPCharacter findById(Long id);
}

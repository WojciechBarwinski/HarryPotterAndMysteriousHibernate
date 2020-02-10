package repositories;

import entities.HPCharacter;

public interface CharacterRepository {

    HPCharacter add(HPCharacter HPCharacter);

    HPCharacter findById(Long id);

    HPCharacter update(HPCharacter hpCharacter);
    HPCharacter updateById(Long id);

    void delete(HPCharacter characterToDelete);
}

package services;

import dto.HPCharacterDto;
import entities.HPCharacter;

import java.util.List;

public interface HpCharacterService {

    List<HPCharacterDto> getAllCharacters();
}

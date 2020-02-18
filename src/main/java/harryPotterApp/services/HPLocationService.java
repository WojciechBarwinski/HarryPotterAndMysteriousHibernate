package harryPotterApp.services;

import harryPotterApp.dto.HPLocationDto;
import harryPotterApp.entities.HPCharacter;

import java.util.List;

public interface HPLocationService {
    List<HPLocationDto> getAllLocations();
    List<HPCharacter> getAllCharactersWithoutLocation();
    void addResidentToLocation(String characterId, String locationId);

}

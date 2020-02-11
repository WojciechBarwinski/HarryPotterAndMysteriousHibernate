package harryPotterApp.repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;

import java.util.List;

public interface HPLocationRepository {
    HPLocation addLocation(HPLocation hpLocation);
    HPLocation findLocationById(Long id);
    HPLocation updateLocationById(Long id);
    void deleteLocationById(Long id);
    List<HPCharacter> getAllResidents(Long id);
}

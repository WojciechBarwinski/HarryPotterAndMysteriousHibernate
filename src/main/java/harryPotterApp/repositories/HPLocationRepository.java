package harryPotterApp.repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;

import java.util.List;

public interface HPLocationRepository {
    HPLocation add(HPLocation hpLocation);
    HPLocation findById(Long id);
    HPLocation updateById(Long id);
    void deleteById(Long id);
    List<HPCharacter> getAllResidents(Long id);
    HPLocation findByCharacterId(Long id);
}

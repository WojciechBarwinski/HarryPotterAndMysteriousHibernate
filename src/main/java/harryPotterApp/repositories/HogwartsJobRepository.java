package harryPotterApp.repositories;

import harryPotterApp.entities.HogwartsJob;

import java.util.List;

public interface HogwartsJobRepository {

    HogwartsJob add(HogwartsJob hogwartsJob);
    HogwartsJob findById(Long id);
    HogwartsJob updateById(Long id);
    void deleteById(Long id);
    HogwartsJob findJobByName(String jobName);
    List <HogwartsJob> findJobByIdCharacter(Long id);


}

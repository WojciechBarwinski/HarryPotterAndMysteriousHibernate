package repositories;

import entities.HogwartsJob;

public interface HogwartsJobRepository {

    HogwartsJob add(HogwartsJob hogwartsJob);
    HogwartsJob findById(Long id);
    HogwartsJob updateById(Long id);
    void deleteById(Long id);
    HogwartsJob findJobByName(String jobName);


}

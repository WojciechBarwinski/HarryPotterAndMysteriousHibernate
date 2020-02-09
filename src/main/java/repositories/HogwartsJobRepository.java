package repositories;

import entities.HogwartsJob;

public interface HogwartsJobRepository {

    HogwartsJob create(HogwartsJob hogwartsJob);
    HogwartsJob readById(Long id);
    HogwartsJob upDate(Long id);
    void deleteById(Long id);


}

package repositories;

import entities.HogwartsJob;

public interface HogwartsJobRepository {

    HogwartsJob create(HogwartsJob hogwartsJob);
    HogwartsJob readById(Long id);
    HogwartsJob readByJob(HogwartsJob hogwartsJob);
    HogwartsJob upDate(HogwartsJob hogwartsJob);
    void delete(HogwartsJob hogwartsJob);
    void deleteById(Long id);


}

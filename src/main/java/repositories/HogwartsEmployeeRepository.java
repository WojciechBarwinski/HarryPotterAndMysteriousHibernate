package repositories;

import entities.HogwartsEmployee;

public interface HogwartsEmployeeRepository {

    HogwartsEmployee create(HogwartsEmployee hogwartsEmployee);
    HogwartsEmployee readById(Long id);
    HogwartsEmployee upDate(HogwartsEmployee hogwartsEmployee);
    void delete(HogwartsEmployee hogwartsEmployee);
    void deleteById(Long id);


}

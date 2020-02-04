package repositories;

import entities.HogwartsEmployee;

public interface HogwartsEmployeeRepository {

    HogwartsEmployee create(HogwartsEmployee hogwartsEmployee);
    HogwartsEmployee readById(Long id);
    HogwartsEmployee upDate(HogwartsEmployee hogwartsEmployee);
    HogwartsEmployee delete(HogwartsEmployee hogwartsEmployee);

    HogwartsEmployee upDateById(Long id);
    HogwartsEmployee deleteById(Long id);


}

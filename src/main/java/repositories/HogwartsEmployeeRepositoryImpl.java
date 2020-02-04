package repositories;

import entities.HogwartsEmployee;

import javax.persistence.EntityManager;
import java.util.Objects;

public class HogwartsEmployeeRepositoryImpl implements HogwartsEmployeeRepository {

    private EntityManager em;

    public HogwartsEmployeeRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public HogwartsEmployee create(HogwartsEmployee hogwartsEmployee) {
        if (Objects.isNull(hogwartsEmployee.getId())) {
            em.persist(hogwartsEmployee);
        }
        return hogwartsEmployee;
    }

    @Override
    public HogwartsEmployee readById(Long id) {
        return null;
    }

    @Override
    public HogwartsEmployee upDate(HogwartsEmployee hogwartsEmployee) {
        return null;
    }

    @Override
    public HogwartsEmployee delete(HogwartsEmployee hogwartsEmployee) {
        return null;
    }

    @Override
    public HogwartsEmployee upDateById(Long id) {
        return null;
    }

    @Override
    public HogwartsEmployee deleteById(Long id) {
        return null;
    }
}

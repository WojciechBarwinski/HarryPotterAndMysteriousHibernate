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
        HogwartsEmployee foundHogwartsEmployee = em.find(HogwartsEmployee.class, id);
        return foundHogwartsEmployee;
    }

    @Override
    public HogwartsEmployee upDate(HogwartsEmployee hogwartsEmployee) {
        HogwartsEmployee upDateEmployee = em.merge(hogwartsEmployee);
        return upDateEmployee;
    }

    @Override
    public void delete(HogwartsEmployee hogwartsEmployee) {
        em.remove(hogwartsEmployee);
    }

    @Override
    public void deleteById(Long id) {
        delete(readById(id));
    }
}

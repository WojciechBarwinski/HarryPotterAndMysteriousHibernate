package repositories;

import entities.HogwartsJob;

import javax.persistence.EntityManager;
import java.util.Objects;

public class HogwartsJobRepositoryImpl implements HogwartsJobRepository {

    private EntityManager em;

    public HogwartsJobRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public HogwartsJob create(HogwartsJob hogwartsJob) {
        if (Objects.isNull(hogwartsJob.getId())) {
            em.persist(hogwartsJob);
        }
        return hogwartsJob;
    }

    @Override
    public HogwartsJob readById(Long id) {
        return em.find(HogwartsJob.class, id);
    }


    @Override
    public HogwartsJob updateById(Long id) {
        HogwartsJob hogwartsJob = readById(id);
        return em.merge(hogwartsJob);
    }

    @Override
    public void deleteById(Long id) {
        HogwartsJob hogwartsJob = readById(id);
        em.remove(hogwartsJob);
    }
}

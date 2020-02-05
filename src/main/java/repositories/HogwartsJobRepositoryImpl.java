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
        HogwartsJob foundHogwartsJob = em.find(HogwartsJob.class, id);
        return foundHogwartsJob;
    }

    @Override
    public HogwartsJob readByJob(HogwartsJob hogwartsJob) {
        HogwartsJob foundHogwartsJob = em.find(HogwartsJob.class, hogwartsJob.getId());
        return foundHogwartsJob;
    }

    @Override
    public HogwartsJob upDate(HogwartsJob hogwartsJob) {
        HogwartsJob upDateEmployee = em.merge(hogwartsJob);
        return upDateEmployee;
    }


    @Override
    public void delete(HogwartsJob hogwartsJob) {
        em.remove(hogwartsJob);
    }

    @Override
    public void deleteById(Long id) {
        delete(readById(id));
    }
}

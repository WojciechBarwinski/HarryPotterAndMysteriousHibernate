package harryPotterApp.repositories;

import harryPotterApp.entities.HogwartsJob;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class HogwartsJobRepositoryImpl implements HogwartsJobRepository {

    private EntityManager em;

    public HogwartsJobRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public HogwartsJob add(HogwartsJob hogwartsJob) {
        if (Objects.isNull(hogwartsJob.getId())) {
            em.persist(hogwartsJob);
        }
        return hogwartsJob;
    }

    @Override
    public HogwartsJob findById(Long id) {
        return em.find(HogwartsJob.class, id);
    }

    @Override
    public HogwartsJob updateById(Long id) {
        return em.merge(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        em.remove(findById(id));
    }

    @Override
    public List <HogwartsJob> findJobByIdCharacter(Long id) {
        Query query = em.createQuery("SELECT job FROM HogwartsJob as job JOIN job.employees e where e.id =:id").setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public HogwartsJob findJobByName(String jobName) {
        String x = "Elixir Master";
        Query nativeQuery = em.createNativeQuery("SELECT hj.positionName, hj.salary FROM characters_jobs as cj" +
                " JOIN hpcharacter as hp on cj.employees_id = hp.id JOIN hogwarts_jobs as hj on cj.positions_id = hj.id" +
                " Where positionName = ?1").setParameter(1, x);
        List<HogwartsJob> hogwartsJobList = nativeQuery.getResultList();
        return hogwartsJobList.get(0);
    }
}

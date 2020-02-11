package repositories;

import entities.HPCharacter;
import entities.HPLocation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HPLocationRepositoryImpl implements HPLocationRepository {

    private EntityManager em;

    public HPLocationRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public HPLocation addLocation(HPLocation hpLocation) {
        if (Objects.isNull(hpLocation.getId())) {
            em.persist(hpLocation);
        }
        return hpLocation;
    }

    @Override
    public HPLocation findLocationById(Long id) {
        return em.find(HPLocation.class, id);
    }

    @Override
    public HPLocation updateLocationById(Long id) {
        return em.merge(findLocationById(id));
    }

    @Override
    public void deleteLocationById(Long id) {
        em.remove(findLocationById(id));
    }

    @Override
    public List<HPCharacter> getAllResidents(Long id) {
        HPLocation locationById = findLocationById(id);
        return new ArrayList<>(locationById.getResidents());
    }
}

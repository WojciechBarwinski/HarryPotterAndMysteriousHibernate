package harryPotterApp.repositories;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HPLocationRepositoryImpl implements HPLocationRepository {

    private EntityManager em;
    private CharacterRepository characterRepository = new CharacterRepositoryImpl(em);

    public HPLocationRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public HPLocation add(HPLocation hpLocation) {
        if (Objects.isNull(hpLocation.getId())) {
            em.persist(hpLocation);
        }
        return hpLocation;
    }

    @Override
    public HPLocation findById(Long id) {
        return em.find(HPLocation.class, id);
    }

    @Override
    public HPLocation updateById(Long id) {
        return em.merge(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        em.remove(findById(id));
    }

    @Override
    public List<HPCharacter> getAllResidents(Long id) {
        HPLocation locationById = findById(id);
        return new ArrayList<>(locationById.getResidents());
    }

    @Override
    public HPLocation findByCharacterId(Long id) {
        Query query = em.createQuery("SELECT location FROM HPLocation as location JOIN location.residents res where res.id =:id").setParameter("id", id);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (HPLocation) query.getSingleResult();
    }

    @Override
    public List<HPLocation> getAllLocation() {
        TypedQuery<HPLocation> allHpLocationFromDB = em.createQuery("SELECT location FROM HPLocation location", HPLocation.class);
        return allHpLocationFromDB.getResultList();
    }

    @Override
    public List<HPCharacter> getAllCharactersWithoutLocation() {
        TypedQuery<HPCharacter> query = em.createQuery("SELECT character FROM HPCharacter as character WHERE character.location IS NULL", HPCharacter.class);
        return query.getResultList();
    }
}

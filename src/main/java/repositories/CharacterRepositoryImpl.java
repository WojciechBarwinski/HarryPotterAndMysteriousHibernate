package repositories;

import entities.HPCharacter;

import javax.persistence.EntityManager;
import java.util.Objects;


public class CharacterRepositoryImpl implements CharacterRepository {
    private EntityManager em;

    public CharacterRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public HPCharacter create(HPCharacter hpCharacter) {
        if (Objects.isNull(hpCharacter.getId())) {
            em.persist(hpCharacter);
        }
        return hpCharacter;
    }

    @Override
    public HPCharacter findById(Long id) {
        HPCharacter foundCharacter = em.find(HPCharacter.class, id);
        return foundCharacter;
    }
    @Override
    public HPCharacter modify(HPCharacter characterToModify) {

        HPCharacter mergedCharacter = em.merge(characterToModify);
        return mergedCharacter;
    }

    @Override
    public void delete(HPCharacter characterToDelete) {
        em.remove(characterToDelete);
    }
}

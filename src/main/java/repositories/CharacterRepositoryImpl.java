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
    public HPCharacter add(HPCharacter hpCharacter) {
        if (Objects.isNull(hpCharacter.getId())) {
            em.persist(hpCharacter);
        }
        return hpCharacter;
    }

    @Override
    public HPCharacter findById(Long id) {
        return em.find(HPCharacter.class, id);
    }
    @Override
    public HPCharacter update(HPCharacter characterToModify) {
        return em.merge(characterToModify);
    }

    @Override
    public HPCharacter updateById(Long id) {
        return em.merge(findById(id));
    }

    @Override
    public void delete(HPCharacter characterToDelete) {
        em.remove(characterToDelete);
    }
}

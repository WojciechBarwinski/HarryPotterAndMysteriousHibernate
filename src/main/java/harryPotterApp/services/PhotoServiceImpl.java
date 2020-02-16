package harryPotterApp.services;

import harryPotterApp.entities.HPCharacter;
import harryPotterApp.repositories.*;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PhotoServiceImpl implements PhotoService {
    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private CharacterRepository characterRepository = new CharacterRepositoryImpl(em);
    private HogwartsJobRepository hogwartsJobRepository = new HogwartsJobRepositoryImpl(em);
    private StudentRepository studentRepository = new StudentRepositoryImpl(em);
    private HPLocationRepository locationRepository = new HPLocationRepositoryImpl(em);
    private PetRepository petRepository = new PetRepositoryImpl(em);
    private ItemRepository itemRepository = new ItemRepositoryImpl(em);


    @Override
    public void addPhotoToCharacter(String path, Long characterId) {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter character = characterRepository.findById(characterId);
        character.setImagePath(path);
        transaction.begin();
        characterRepository.update(character);
        transaction.commit();
    }
}

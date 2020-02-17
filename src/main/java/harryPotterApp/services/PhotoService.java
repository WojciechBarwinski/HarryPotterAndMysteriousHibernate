package harryPotterApp.services;

import harryPotterApp.entities.EntityType;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;
import harryPotterApp.entities.Pet;
import harryPotterApp.repositories.*;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PhotoService {
    private static EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private static CharacterRepository characterRepository = new CharacterRepositoryImpl(em);
    private static HPLocationRepository locationRepository = new HPLocationRepositoryImpl(em);
    private static PetRepository petRepository = new PetRepositoryImpl(em);

    public static void addPhoto(String path, Long objectId, EntityType entityType) {
        EntityTransaction transaction = em.getTransaction();
        switch (entityType) {
            case CHARACTER:
                addPhotoToCharacter(path, objectId, transaction);
                break;
            case PET:
                addPhotoToPet(path, objectId, transaction);
                break;
            case LOCATION:
                addPhotoToLocation(path, objectId, transaction);
                break;
        }
    }

    private static void addPhotoToLocation(String path, Long locationId, EntityTransaction transaction) {
        HPLocation location = locationRepository.findById(locationId);
        location.setImagePath(path);
        transaction.begin();
        locationRepository.updateById(locationId);
        transaction.commit();
    }

    private static void addPhotoToPet(String path, Long petId, EntityTransaction transaction) {
        Pet pet = petRepository.findById(petId);
        pet.setImagePath(path);
        transaction.begin();
        petRepository.updateById(petId);
        transaction.commit();
    }

    private static void addPhotoToCharacter(String path, Long characterId, EntityTransaction transaction) {
        HPCharacter character = characterRepository.findById(characterId);
        character.setImagePath(path);
        transaction.begin();
        characterRepository.update(character);
        transaction.commit();
    }
}

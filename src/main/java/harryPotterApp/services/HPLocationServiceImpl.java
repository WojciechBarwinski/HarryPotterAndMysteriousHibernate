package harryPotterApp.services;

import harryPotterApp.dto.HPLocationDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;
import harryPotterApp.mappers.HPLocationMapper;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.HPLocationRepository;
import harryPotterApp.repositories.HPLocationRepositoryImpl;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class HPLocationServiceImpl implements HPLocationService {
    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private HPLocationRepository locationRepository = new HPLocationRepositoryImpl(em);
    private CharacterRepository characterRepository = new CharacterRepositoryImpl(em);

    @Override
    public List<HPLocationDto> getAllLocations() {
        em.clear();
        return locationRepository.getAllLocation()
                .stream()
                .map(HPLocationMapper::mapToHPLocationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HPCharacter> getAllCharactersWithoutLocation() {
        return locationRepository.getAllCharactersWithoutLocation();
    }

    @Override
    public void addResidentToLocation(String characterId, String locationId) {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter residentToAdd = characterRepository.findById(Long.parseLong(characterId));
        HPLocation chosenLocation = locationRepository.findById(Long.parseLong(locationId));
        transaction.begin();
        residentToAdd.setLocation(chosenLocation);
        characterRepository.update(residentToAdd);
        transaction.commit();
    }

    @Override
    public void removeResident(Long characterId) {
        EntityTransaction transaction = em.getTransaction();
        HPCharacter character = characterRepository.findById(characterId);
        character.setLocation(null);
        transaction.begin();
        characterRepository.update(character);
        transaction.commit();
    }
}

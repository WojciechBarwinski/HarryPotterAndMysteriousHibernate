package harryPotterApp.services;

import harryPotterApp.dto.HPLocationDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.mappers.HPLocationMapper;
import harryPotterApp.repositories.HPLocationRepository;
import harryPotterApp.repositories.HPLocationRepositoryImpl;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class HPLocationServiceImpl implements HPLocationService {
    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private HPLocationRepository locationRepository = new HPLocationRepositoryImpl(em);

    @Override
    public List<HPLocationDto> getAllLocations() {
        return locationRepository.getAllLocation()
                .stream()
                .map(HPLocationMapper::mapToHPLocationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HPCharacter> getAllCharactersWithoutLocation() {
        return locationRepository.getAllCharactersWithoutLocation();
    }
}

package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.dto.PetDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.Pet;
import harryPotterApp.mappers.HPCharacterMapper;
import harryPotterApp.mappers.PetMapper;
import harryPotterApp.repositories.PetRepository;
import harryPotterApp.repositories.PetRepositoryImpl;
import harryPotterApp.startingData.SingletonEntityManagerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetServiceImpl implements PetService {
    private EntityManager em = SingletonEntityManagerFactory.getEmf().createEntityManager();
    private PetRepository petRepository = new PetRepositoryImpl(em);

    @Override
    public List<PetDto> getAllPets() {
        List<Pet> allPets = petRepository.getAllPets();
        List<PetDto> petsDto = allPets.stream().map(PetMapper::mapToPetDto).collect(Collectors.toList());
        return petsDto;
    }

    @Override
    public List<PetDto> findById(String id) {
        List<PetDto> foundPets = new ArrayList<>();
        Pet foundPet = petRepository.findById(Long.valueOf(id));
        foundPets.add(PetMapper.mapToPetDto(foundPet));
        return foundPets;
    }

    @Override
    public List<HPCharacterDto> getAllCharactersWithoutPet() {
        List<HPCharacter> allCharactersWithoutPet = petRepository.getAllCharactersWithoutPet();
        List<HPCharacterDto> charactersWithoutPet = allCharactersWithoutPet
                .stream()
                .map(HPCharacterMapper::mapToHPCharacterDto)
                .collect(Collectors.toList());
        return charactersWithoutPet;
    }
}

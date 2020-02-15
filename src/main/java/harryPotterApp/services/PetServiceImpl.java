package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.dto.PetDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.Pet;
import harryPotterApp.mappers.HPCharacterMapper;
import harryPotterApp.mappers.PetMapper;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.repositories.PetRepository;
import harryPotterApp.repositories.PetRepositoryImpl;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetServiceImpl implements PetService {
    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private PetRepository petRepository = new PetRepositoryImpl(em);
    CharacterRepository characterRepository = new CharacterRepositoryImpl(em);

    @Override
    public List<PetDto> getAllPets() {
        List<Pet> allPets = petRepository.getAllPets();
        return allPets.stream().map(PetMapper::mapToPetDto).collect(Collectors.toList());
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
        return allCharactersWithoutPet.stream()
                .map(HPCharacterMapper::mapToHPCharacterDto)
                .collect(Collectors.toList());
    }

    @Override
    public void add(String name, String species, String ownerData) {
        Long ownerId = getOwnerIdByFirstAndLastName(ownerData);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        HPCharacter foundedOwner = characterRepository.findById(ownerId);
        petRepository.add(new Pet(name,foundedOwner,species));
        transaction.commit();
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        petRepository.deletedById(id);
        transaction.commit();
    }

    public Long getOwnerIdByFirstAndLastName(String ownerData){
        String[] owner = ownerData.split(" ");
        String firstName = owner[0];
        String lastName = owner[1];

        List<HPCharacter> allCharacters = characterRepository.getAllCharacters();
        return allCharacters.stream()
                .filter(hpCharacterDto -> hpCharacterDto.getFirstName().equals(firstName))
                .filter(hpCharacterDto -> hpCharacterDto.getLastName().equals(lastName))
                .map(HPCharacter::getId)
                .findFirst().get();

    }
}

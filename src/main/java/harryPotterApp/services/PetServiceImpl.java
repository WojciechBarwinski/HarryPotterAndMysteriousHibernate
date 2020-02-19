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
    private CharacterRepository characterRepository = new CharacterRepositoryImpl(em);
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    @Override
    public List<PetDto> getAllPets() {
        return petRepository.getAllPets().stream().map(PetMapper::mapToPetDto).collect(Collectors.toList());
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
        return petRepository.getAllCharactersWithoutPet()
                .stream()
                .map(HPCharacterMapper::mapToHPCharacterDto)
                .collect(Collectors.toList());
    }

    @Override
    public void add(String name, String species, String ownerData) {
        EntityTransaction transaction = em.getTransaction();
        Long ownerId = hpCharacterService.getCharacterIdByFirstAndLastName(ownerData);
        transaction.begin();
        HPCharacter foundedOwner = characterRepository.findById(ownerId);
        petRepository.add(new Pet(name, foundedOwner, species));
        transaction.commit();
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        petRepository.deletedById(id);

        transaction.commit();
    }


    @Override
    public PetDto preparePetForViewPage(Long id) {
        em.clear();
        Pet petFromDB = petRepository.findById(id);
        return PetMapper.mapToPetDto(petFromDB);
    }
}

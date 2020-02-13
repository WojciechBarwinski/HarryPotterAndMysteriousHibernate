package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.mappers.HPCharacterMapper;
import harryPotterApp.repositories.CharacterRepositoryImpl;
import harryPotterApp.startingData.SingletonEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HpCharacterServiceImpl implements HpCharacterService {
    //private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em = SingletonEntityManagerFactory.getEmf().createEntityManager();
    private CharacterRepository characterRepository = new CharacterRepositoryImpl(em);

    @Override
    public List<HPCharacterDto> getAllCharacters() {
        List<HPCharacter> allCharacters = characterRepository.getAllCharacters();
        List<HPCharacterDto> allCharactersDto = new ArrayList<>();
        for (HPCharacter hpCharacter : allCharacters) {
            allCharactersDto.add(HPCharacterMapper.mapToHPCharacterDto(hpCharacter));
        }
        return allCharactersDto;
    }

    @Override
    public void addCharacter(String firstName, String lastName, LocalDate birthDate) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        characterRepository.add(new HPCharacter(firstName, lastName, birthDate));
        transaction.commit();
    }

    @Override
    public void deleteCharacterById(Long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        characterRepository.deleteById(id);
        transaction.commit();
    }

    @Override
    public List<HPCharacterDto> findCharacterById(String id) {
        List<HPCharacterDto> foundedCharacter = new ArrayList<>();
        HPCharacter character = characterRepository.findById(Long.valueOf(id));
        foundedCharacter.add(HPCharacterMapper.mapToHPCharacterDto(character));
        return foundedCharacter;
    }

    @Override
    public HPCharacterDto findCharacterToView(Long id) {
        HPCharacter character = characterRepository.findById(id);
        return  HPCharacterMapper.mapToHPCharacterDto(character);
    }


}

package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.repositories.CharacterRepository;
import harryPotterApp.mappers.HPCharacterMapper;
import harryPotterApp.repositories.CharacterRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class HpCharacterServiceImpl implements HpCharacterService {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORM");
    private EntityManager em = emf.createEntityManager();
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
}

package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;
import harryPotterApp.entities.HPLocation;
import harryPotterApp.entities.HogwartsJob;
import harryPotterApp.entities.Student;
import harryPotterApp.repositories.*;
import harryPotterApp.mappers.HPCharacterMapper;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HpCharacterServiceImpl implements HpCharacterService {

    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private CharacterRepository characterRepository = new CharacterRepositoryImpl(em);
    private HogwartsJobRepository hogwartsJobRepository = new HogwartsJobRepositoryImpl(em);
    private StudentRepository studentRepository = new StudentRepositoryImpl(em);
    private HPLocationRepository locationRepository = new HPLocationRepositoryImpl(em);

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
    public void addCharacter(String firstName, String lastName, String birthDate) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        characterRepository.add(new HPCharacter(firstName, lastName, LocalDate.parse(birthDate)));
        transaction.commit();
    }

    @Override
    public void deleteCharacterById(Long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        characterRepository.deleteById(id);
        transaction.commit();
    }

    /* TODO
    * It's list because we add find by name/last name, and there it will by list.*/
    @Override
    public List<HPCharacterDto> findCharacterById(String id) {
        List<HPCharacterDto> foundedCharacter = new ArrayList<>();
        HPCharacter character = characterRepository.findById(Long.valueOf(id));
        foundedCharacter.add(HPCharacterMapper.mapToHPCharacterDto(character));
        return foundedCharacter;
    }

    @Override
    public HPCharacterDto prepareCharacterToView(Long id) {
        HPCharacter character = characterRepository.findById(id);
        HPCharacterDto hpCharacterDto = HPCharacterMapper.mapToHPCharacterDto(character);

        Student studentByIdCharacter = studentRepository.findStudentByIdCharacter(id);
        List<HogwartsJob> jobByIdCharacter = hogwartsJobRepository.findJobByIdCharacter(id);
        HPLocation location = locationRepository.findByCharacterId(id);

      if (!(studentByIdCharacter == null)){
          hpCharacterDto.setStudent(studentByIdCharacter);
      }
      if (!jobByIdCharacter.isEmpty()){
          hpCharacterDto.setHogwartsJob(jobByIdCharacter);
      }
      if (!(location == null)){
          hpCharacterDto.setLocation(location.getLocationName());
      }

        return hpCharacterDto;
    }

}

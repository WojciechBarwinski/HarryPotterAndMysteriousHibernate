package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.*;
import harryPotterApp.mappers.HPCharacterMapper;
import harryPotterApp.repositories.*;
import harryPotterApp.startingData.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HpCharacterServiceImpl implements HpCharacterService {

    private EntityManager em = EntityManagerFactory.getEmf().createEntityManager();
    private CharacterRepository characterRepository = new CharacterRepositoryImpl(em);
    private HogwartsJobRepository hogwartsJobRepository = new HogwartsJobRepositoryImpl(em);
    private StudentRepository studentRepository = new StudentRepositoryImpl(em);
    private HPLocationRepository locationRepository = new HPLocationRepositoryImpl(em);
    private ItemRepository itemRepository = new ItemRepositoryImpl(em);

    @Override
    public List<HPCharacterDto> getAllCharacters() {
        em.clear();
        return characterRepository.getAllCharacters()
                .stream()
                .map(HPCharacterMapper::mapToHPCharacterDto)
                .collect(Collectors.toList());
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
    public List<HPCharacterDto> findCharacterByFirstNameOrLastName(String name) {
        List<HPCharacter> allCharacters = characterRepository.getAllCharacters();
        return allCharacters
                .stream()
                .filter(hpCharacter -> hpCharacter.getFirstName().equalsIgnoreCase(name) || hpCharacter.getLastName().equalsIgnoreCase(name))
                .map(HPCharacterMapper::mapToHPCharacterDto)
                .collect(Collectors.toList());
    }

    //TODO w wolnej chwili jak wszystko inne działa XD
    @Override
    public HPCharacterDto prepareCharacterToView(Long id) {
        em.clear();
        HPCharacter character = characterRepository.findById(id);
        HPCharacterDto hpCharacterDto = HPCharacterMapper.mapToHPCharacterDto(character);

        Student studentByIdCharacter = studentRepository.findStudentByIdCharacter(id);
        List<HogwartsJob> jobByIdCharacter = hogwartsJobRepository.findJobByIdCharacter(id);
        HPLocation location = locationRepository.findByCharacterId(id);
        List<Item> itemList = itemRepository.getItemByOwnerID(id);

        if (!(studentByIdCharacter == null)) {
            hpCharacterDto.setStudent(studentByIdCharacter);
        }
        if (!jobByIdCharacter.isEmpty()) {
            hpCharacterDto.setHogwartsJob(jobByIdCharacter);
        }
        if (!(location == null)) {
            hpCharacterDto.setLocation(location.getLocationName());
        }
        if (!itemList.isEmpty()) {
            hpCharacterDto.setItemList(itemList);
        }
        return hpCharacterDto;
    }

    @Override
    public Long getCharacterIdByFirstAndLastName(String characterData) {
        String[] character = characterData.split(" ");
        String firstName = character[0];
        String lastName = character[1];
        return characterRepository.getAllCharacters()
                .stream()
                .filter(hpCharacterDto -> hpCharacterDto.getFirstName().equals(firstName))
                .filter(hpCharacterDto -> hpCharacterDto.getLastName().equals(lastName))
                .map(HPCharacter::getId)
                .findFirst().get();
    }

    @Override
    public void updateCharacter(String characterId, String firstName, String lastName, String birthDate) {
        EntityTransaction transaction = em.getTransaction();
        Long id = Long.valueOf(characterId);
        HPCharacter characterToUpdate = characterRepository.findById(id);

        transaction.begin();
        if (!firstName.isBlank()){
            characterToUpdate.setFirstName(firstName);
        }
        if (!lastName.isBlank()){
            characterToUpdate.setLastName(lastName);
        }
        if (!birthDate.isBlank()){
           characterToUpdate.setBirthDate( LocalDate.parse(birthDate));
        }
        characterRepository.updateById(id);
        transaction.commit();
    }
}

package harryPotterApp.services;

import harryPotterApp.dto.CharacterToUpdate;
import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class ValidationService {
    private static HpCharacterService hpCharacterService = new HpCharacterServiceImpl();
    private static String baseRegex = "^[A-Z][a-z]{2,15}$";
    private static String imagePathRegex = "^https:.*(jpg$)|(png$)";
    private static String itemRegex = "^[A-Z].{2,20}$";


    public static Map<String, String> searchValidate(String userInput, String searchFilter) {
        Map<String, String> errorsMap = new HashMap<>();
        if (userInput.isBlank() || Objects.isNull(searchFilter)) {
            errorsMap.put("noInput", "You have not entered all input");
        } else {
            if (searchFilter.equals("firstName") && !isFirstNamePresent(userInput)) {
                errorsMap.put("invalidData", "Searched first name doesn't exist");
            }
            if (searchFilter.equals("lastName") && !isLastNamePresent(userInput)) {
                errorsMap.put("invalidData", "Searched last name doesn't exist");
            }
        }
        return errorsMap;
    }

    private static boolean isLastNamePresent(String lastName) {
        return hpCharacterService.getAllCharacters()
                .stream()
                .map(HPCharacterDto::getLastName)
                .anyMatch(characterLastName -> characterLastName.equalsIgnoreCase(lastName));
    }

    private static boolean isFirstNamePresent(String firstName) {
        return hpCharacterService.getAllCharacters()
                .stream()
                .map(HPCharacterDto::getFirstName)
                .anyMatch(characterFirstName -> characterFirstName.equalsIgnoreCase(firstName));
    }

    public static Map<String, String> searchByIdValidate(String userInput) {
        Map<String, String> errorsMap = new HashMap<>();
        if (userInput.isBlank()) {
            errorsMap.put("noId", "You have not entered any Id");
        } else if (!isIdPresent(userInput)) {
            errorsMap.put("invalidId", "Searched Id doesn't exist");
        }
        return errorsMap;
    }

    private static boolean isIdPresent(String id) {
        return hpCharacterService.getAllCharacters()
                .stream()
                .map(HPCharacterDto::getId)
                .anyMatch(characterId -> characterId.equals(Long.valueOf(id)));
    }

    public static Map<String, String> addValidate(String firstName, String lastName, String birthDate) {
        Map<String, String> errorsMap = new HashMap<>();
        if (firstName.isBlank() || lastName.isBlank() || birthDate.isBlank()) {
            errorsMap.put("noValue", "You dont entered all necessary");
        } else {
            nameValidate(firstName, errorsMap);
            if (!lastName.matches(baseRegex)) {
                errorsMap.put("wrongLastName", "You put invalid last name");
            }
            if (LocalDate.parse(birthDate).isAfter(LocalDate.now())) {
                errorsMap.put("wrongData", "You must put date before today");
            }
            if (characterExists(firstName, lastName)) {
                errorsMap.put("characterExists", "Character already exists");
            }
        }
        return errorsMap;
    }

    private static void nameValidate(String name, Map<String, String> errorsMap) {
        if (!name.matches(baseRegex)) {
            errorsMap.put("wrongName", "You put invalid first name");
        }
    }

    private static boolean characterExists(String firstName, String lastName) {
        return hpCharacterService.getAllCharacters()
                .stream()
                .anyMatch(hpCharacterDto -> hpCharacterDto.getFirstName().equals(firstName)
                        && hpCharacterDto.getLastName().equals(lastName));
    }

    public static Map<String, String> addingPetValidate(String name, String species, String owner) {
        Map<String, String> errorsMap = new HashMap<>();
        if (name.isBlank() || species.isBlank() || owner == null) {
            errorsMap.put("noValue", "You dont entered all necessary data");
        } else {
            nameValidate(name, errorsMap);
            if (!species.matches(baseRegex)) {
                errorsMap.put("wrongSpecies", "You put invalid species");
            }
        }
        return errorsMap;
    }

    public static Map<String, String> imagePathValidate(String imagePath) {
        Map<String, String> errorsMap = new HashMap<>();
        if (imagePath.isBlank()) {
            errorsMap.put("noValue", "You dont entered all necessary data");
        } else if (!imagePath.matches(imagePathRegex)) {
            errorsMap.put("wrongPath", "You put invalid image path");
        }
        return errorsMap;
    }

    public static Map<String, String> selectInputValidate(String input) {
        Map<String, String> errorsMap = new HashMap<>();
        if (input == null) {
            errorsMap.put("noSelect", "You didn't select any resident");
        }
        return errorsMap;
    }

    public static Map<String, String> addItemValidate(String itemName, String itemValue) {
        Map<String, String> errorsMap = new HashMap<>();
        if (itemName.isBlank() || itemValue.isBlank()) {
            errorsMap.put("noValue", "You did not enter all necessary date");
        } else if (!itemName.matches(itemRegex)) {
            errorsMap.put("wrongName", "You put invalid item name");
        }
        return errorsMap;
    }

    public static Map<String, String> dataToUpdateValidate(CharacterToUpdate character) {
        Map<String, String> errorsMap = new HashMap<>();
        String id = character.getId();

        if (character.getFirstName().isBlank() && character.getLastName().isBlank() && character.getBirthDate().isBlank()) {
            errorsMap.put("noValue", "You dont entered any necessary date");
        } else {
            if (!character.getFirstName().isBlank()) {
                nameValidate(character.getFirstName(), errorsMap);
            }

            if (!character.getLastName().isBlank()) {
                if (!character.getLastName().matches(baseRegex)) {
                    errorsMap.put("wrongLastName", "You put invalid last name");
                }
            }

            if (!character.getBirthDate().isBlank()) {
                if (LocalDate.parse(character.getBirthDate()).isAfter(LocalDate.now())) {
                    errorsMap.put("wrongData", "You must put date before today");
                }
            }
        }
        if (errorsMap.isEmpty() && findReWriteCharacter(id, character)) {
            errorsMap.put("characterExist", "This character already exist");
        }
        return errorsMap;
    }

    private static boolean findReWriteCharacter(String id, CharacterToUpdate character) {
        HPCharacter originalCharacter = hpCharacterService.findCharacterById(id);
        if (character.getFirstName().isBlank()) {
            character.setFirstName(originalCharacter.getFirstName());
        }
        if (character.getLastName().isBlank()) {
            character.setLastName(originalCharacter.getLastName());
        }
        return characterExists(character.getFirstName(), character.getLastName());
    }
}

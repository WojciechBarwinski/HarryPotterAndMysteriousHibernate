package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ValidationService {
    private static HpCharacterService hpCharacterService = new HpCharacterServiceImpl();
    private static String baseRegex = "^[A-Z][a-z]{2,15}$";// only latter, first big, rest norm, minimum 3 letters, max 10 letters


    public static Map<String, String> searchValidate(String userInput) {
        Map<String, String> errorsMap = new HashMap<>();
        if (userInput.isBlank()) {
            errorsMap.put("noId", "You have not entered any Id");
        } else if (!isIdPresent(userInput)) {
            errorsMap.put("invalidId", "Searched Id doesn't exist");
        }
        return errorsMap;
    }

    private static boolean isIdPresent(String id) {
        List<HPCharacterDto> allCharacters = hpCharacterService.getAllCharacters();
        return allCharacters.stream().map(HPCharacterDto::getId).anyMatch(x -> x.equals(Long.valueOf(id)));
    }

    public static Map<String, String> addValidate(String firstName, String lastName, String birthDate) {
        Map<String, String> errorsMap = new HashMap<>();
        if (firstName.isBlank() || lastName.isBlank() || birthDate.isBlank()) {
            errorsMap.put("noValue", "You dont entered all necessary");
        } else {
            if (!firstName.matches(baseRegex)) {
                errorsMap.put("wrongName", "You put invalid first name");
            }
            if (!lastName.matches(baseRegex)) {
                errorsMap.put("wrongLastName", "You put invalid last name");
            }
            if (LocalDate.parse(birthDate).isAfter(LocalDate.now())) {
                errorsMap.put("wrongData", "You must put date before today");
            }
        }
        return errorsMap;
    }

    public static Map<String, String> validateAddingPet(String name, String species, String owner) {
        Map<String, String> errorsMap = new HashMap<>();
        if (name.isBlank() || species.isBlank() || owner == null) {
            errorsMap.put("noValue", "You dont entered all necessary data");
        } else {
            if (!name.matches(baseRegex)) {
                errorsMap.put("wrongName", "You put invalid name");
            }
            if (!species.matches(baseRegex)) {
                errorsMap.put("wrongSpecies", "You put invalid species");
            }
        }
        return errorsMap;
    }
}

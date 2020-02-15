package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ValidationService {
    private static HpCharacterService hpCharacterService = new HpCharacterServiceImpl();
    private static String firstNameRegex = "^[A-Z][a-z]{2,10}$";// only latter, first big, rest norm, minimum 3 letters, max 10 letters
    private static String lastNameRegex = "^[A-Z][a-z]{3,15}$";// only latter, first big, rest norm, minimum 2 letters, max 10 letters


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

    public static Map<String, String> addValidate(String firstName, String lastName, LocalDate birthDate){
        Map<String, String> errorsMap = new HashMap<>();
        if (firstName.isBlank() || lastName.isBlank() || birthDate.toString().isEmpty()){
            errorsMap.put("noValue", "You dont entered all necessary");
        } else {
            if (!firstName.matches(firstNameRegex)){
                errorsMap.put("wrongName", "You put invalid first name");
            }
            if (!lastName.matches(lastNameRegex)){
                errorsMap.put("wrongLastName", "You put invalid last name");
            }
            if (birthDate.isAfter(LocalDate.now())){
                errorsMap.put("wrongData", "You must put date before today");
            }
        }
        return errorsMap;
    }
}

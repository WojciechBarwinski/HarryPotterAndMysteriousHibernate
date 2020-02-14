package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class ValidationService {
    private static HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    public static Map<String, String> searchValidate(String userInput) {
        Map<String, String> errorsMap = new HashMap<>();
        if (userInput.isBlank()) {
            errorsMap.put("noId", "You have not entered any Id");
        } else if (!isIdPresent(userInput)) {
            errorsMap.put("invalidId", "Searched Id doesn't exist");
        }
        return errorsMap;
    }

    public static Map<String, String> addValidate(String firstName, String lastName, LocalDate birthDate){
        Map<String, String> errorsMap = new HashMap<>();
        if (firstName.isBlank() || lastName.isBlank() || birthDate == null){
            errorsMap.put("noValue", "You dont entered all necessary");
        } else {
            if (!firstName.matches("^[A-Z][a-z]{2,10}$")){
                errorsMap.put("wrongName", "You put invalid first name");
            }
            if (!lastName.matches("^[A-Z][a-z]{2,15}$")){
                errorsMap.put("wrongLastName", "You put invalid last name");
            }
            if (birthDate.isAfter(LocalDate.now())){
                errorsMap.put("wrongData", "You must put date before today");
            }
        }
        return errorsMap;
    }

    private static boolean isIdPresent(String id) {
        List<HPCharacterDto> allCharacters = hpCharacterService.getAllCharacters();
        return allCharacters.stream().map(HPCharacterDto::getId).anyMatch(x -> x.equals(Long.valueOf(id)));
    }
}

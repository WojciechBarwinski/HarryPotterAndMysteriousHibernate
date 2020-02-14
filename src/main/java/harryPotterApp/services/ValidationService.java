package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationService {
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    public Map<String, String> validateSearch(String userInput) {

        Map<String, String> errorsMap = new HashMap<>();
        if (userInput.isBlank()) {
            errorsMap.put("noId", "You have not entered any Id");
        } else if (!isIdPresent(userInput)) {
            errorsMap.put("invalidId", "Searched Id doesn't exist");
        }
        return errorsMap;
    }

    boolean isIdPresent(String id) {
        List<HPCharacterDto> allCharacters = hpCharacterService.getAllCharacters();
        return allCharacters.stream().map(HPCharacterDto::getId).anyMatch(x -> x.equals(Long.valueOf(id)));
    }
}

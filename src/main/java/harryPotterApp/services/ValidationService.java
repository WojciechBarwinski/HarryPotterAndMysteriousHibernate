package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationService {
    private HpCharacterService hpCharacterService = new HpCharacterServiceImpl();

    public Map<String, String> validateSearch(String userInput) {
        List<HPCharacterDto> allCharacters = hpCharacterService.getAllCharacters();
        Map<String, String> errorsMap = new HashMap<>();
        if (userInput.isBlank()) {
            errorsMap.put("noId", "You have not entered any Id");
        } else if (Long.parseLong(userInput) < 1 || Long.parseLong(userInput) > allCharacters.size()){
            errorsMap.put("invalidId", "Searched Id doesn't exist");
        }
        return errorsMap;
    }
}

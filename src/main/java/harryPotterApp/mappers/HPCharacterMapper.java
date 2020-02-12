package harryPotterApp.mappers;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;

public class HPCharacterMapper {

    private HPCharacterMapper() {

    }

    public static HPCharacterDto mapToHPCharacterDto(HPCharacter hpCharacter) {
        HPCharacterDto hpCharacterDto = new HPCharacterDto();

        hpCharacterDto.setId(hpCharacter.getId());
        hpCharacterDto.setFirstName(hpCharacter.getFirstName());
        hpCharacterDto.setLastName(hpCharacter.getLastName());
        hpCharacterDto.setBirthDate(hpCharacter.getBirthDate());
        hpCharacterDto.setPet(hpCharacter.getPet());

        return hpCharacterDto;
    }
}
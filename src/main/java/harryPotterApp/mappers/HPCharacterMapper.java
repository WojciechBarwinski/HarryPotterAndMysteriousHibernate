package harryPotterApp.mappers;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;

import java.util.Objects;

public class HPCharacterMapper {

    private static String altImagePath = "/image/tmpFoto.jpg";
    private HPCharacterMapper() {

    }

    public static HPCharacterDto mapToHPCharacterDto(HPCharacter hpCharacter) {
        HPCharacterDto hpCharacterDto = new HPCharacterDto();

        hpCharacterDto.setId(hpCharacter.getId());
        hpCharacterDto.setFirstName(hpCharacter.getFirstName());
        hpCharacterDto.setLastName(hpCharacter.getLastName());
        hpCharacterDto.setBirthDate(hpCharacter.getBirthDate());
        hpCharacterDto.setPet(hpCharacter.getPet());

        if (Objects.isNull(hpCharacter.getImagePath())){
            hpCharacterDto.setImagePath(altImagePath);
        } else {
            hpCharacterDto.setImagePath(hpCharacter.getImagePath());
        }
        
        return hpCharacterDto;
    }
}

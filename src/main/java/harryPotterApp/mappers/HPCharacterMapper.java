package harryPotterApp.mappers;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;

import java.util.Objects;

public class HPCharacterMapper {

    private static String altImagePath = "/image/tmpFoto.jpg";

    private HPCharacterMapper() {

    }

    public static HPCharacterDto mapToHPCharacterDto(HPCharacter hpCharacter) {
        HPCharacterDto dto = HPCharacterDto.builder()
                .id(hpCharacter.getId())
                .firstName(hpCharacter.getFirstName())
                .lastName(hpCharacter.getLastName())
                .birthDate(hpCharacter.getBirthDate())
                .pet(hpCharacter.getPet())
                .build();
        if (Objects.isNull(hpCharacter.getImagePath())) {
            dto.setImagePath(altImagePath);
        } else {
            dto.setImagePath(hpCharacter.getImagePath());
        }
        return dto;
    }
}

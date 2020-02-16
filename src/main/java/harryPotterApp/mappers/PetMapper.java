package harryPotterApp.mappers;

import harryPotterApp.dto.PetDto;
import harryPotterApp.entities.Pet;

import java.util.Objects;

public class PetMapper {
    private static String altImagePath = "/image/tmpFoto.jpg";

    private PetMapper() {

    }

    public static PetDto mapToPetDto(Pet pet) {
        PetDto dto = PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies())
                .owner(pet.getOwner())
                .build();

        if (Objects.isNull(pet.getImagePath())) {
            dto.setImagePath(altImagePath);
        } else {
            dto.setImagePath(pet.getImagePath());
        }
        return dto;
    }
}

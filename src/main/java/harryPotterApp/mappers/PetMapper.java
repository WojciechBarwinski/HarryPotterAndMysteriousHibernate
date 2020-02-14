package harryPotterApp.mappers;

import harryPotterApp.dto.PetDto;
import harryPotterApp.entities.Pet;

import java.util.Objects;

public class PetMapper {
    private static String altImagePath = "/image/tmpFoto.jpg";

    private PetMapper() {

    }

    public static PetDto mapToPetDto(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setId(pet.getId());
        petDto.setName(pet.getName());
        petDto.setSpecies(pet.getSpecies());
        petDto.setOwner(pet.getOwner());
        if (Objects.isNull(pet.getImagePath())) {
            petDto.setImagePath(altImagePath);
        } else {
            petDto.setImagePath(pet.getImagePath());
        }
        return petDto;
    }
}

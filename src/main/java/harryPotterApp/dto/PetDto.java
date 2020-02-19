package harryPotterApp.dto;

import harryPotterApp.entities.HPCharacter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PetDto {
    private Long id;
    private String name;
    private String species;
    private HPCharacter owner;
    private String imagePath;
}

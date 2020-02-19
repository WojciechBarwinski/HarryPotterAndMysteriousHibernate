package harryPotterApp.dto;

import harryPotterApp.entities.HPCharacter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class HPLocationDto {

    private Long id;
    private String name;
    private Set<HPCharacter> residents;
    private String imagePath;

}

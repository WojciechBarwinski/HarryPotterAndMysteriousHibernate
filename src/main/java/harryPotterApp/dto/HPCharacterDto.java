package harryPotterApp.dto;

import harryPotterApp.entities.HogwartsJob;
import harryPotterApp.entities.Pet;
import harryPotterApp.entities.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class HPCharacterDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Pet pet;
    private String imagePath;
    private List<HogwartsJob> hogwartsJob;
    private Student student;
    private String location;
}

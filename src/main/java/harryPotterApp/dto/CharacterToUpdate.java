package harryPotterApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CharacterToUpdate {

    private String id = "";
    private String firstName = "";
    private String lastName = "";
    private String birthDate = "";



    public CharacterToUpdate(String id, String firstName, String lastName, String birthDate) {
        this.id = id;
        if (!firstName.isBlank()){
            this.firstName = firstName;
        }
        if (!lastName.isBlank()){
            this.lastName = lastName;
        }
        if (!birthDate.isBlank()){
            this.birthDate = birthDate;
        }
    }
}

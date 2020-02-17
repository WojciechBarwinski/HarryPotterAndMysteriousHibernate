package harryPotterApp.services;

import harryPotterApp.dto.HPCharacterDto;
import harryPotterApp.entities.HPCharacter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    private HpCharacterService hpCharacterService;


    @Test
    void searchValidate() {
    }

    @Test
    void addValidate() {
/*        List<HPCharacter> testList = new ArrayList<>();
        testList.add(new HPCharacter("Harry", "Potter", LocalDate.of(1980,7,30)));

        boolean isFirstNamePresent = testList.contains("Harry");
        boolean isLastNamePresent = testList.contains("Potter");

        assertThat(isFirstNamePresent).isTrue();
        assertThat(isLastNamePresent).isTrue();*/

    }

    @Test
    void validateAddingPet() {
    }

    @Test
    void validateImagePath() {
    }
}
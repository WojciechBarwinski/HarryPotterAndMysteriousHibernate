package harryPotterApp.dto;

import harryPotterApp.entities.HogwartsJob;
import harryPotterApp.entities.Pet;
import harryPotterApp.entities.Student;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<HogwartsJob> getHogwartsJob() {
        return hogwartsJob;
    }

    public void setHogwartsJob(List<HogwartsJob> hogwartsJob) {
        this.hogwartsJob = hogwartsJob;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

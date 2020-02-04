package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Characters")
public class HPCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToMany
    @JoinColumn(name = "HogwartsEmployee_id")
    List<HogwartsEmployee> listOfHogwartsEmployee = new ArrayList<>();

    protected HPCharacter(
    ){}

    public HPCharacter(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
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

    public List<HogwartsEmployee> getListOfHogwartsEmployee() {
        return listOfHogwartsEmployee;
    }

    public void setListOfHogwartsEmployee(List<HogwartsEmployee> listOfHogwartsEmployee) {
        this.listOfHogwartsEmployee = listOfHogwartsEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HPCharacter HPCharacter = (HPCharacter) o;
        return Objects.equals(id, HPCharacter.id) &&
                Objects.equals(firstName, HPCharacter.firstName) &&
                Objects.equals(lastName, HPCharacter.lastName) &&
                Objects.equals(birthDate, HPCharacter.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate);
    }
}

package harryPotterApp.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "HPCharacter")
public class HPCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    @NotEmpty
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location", foreignKey = @ForeignKey(name = "FK_hpcharacter_location_id"))
    private HPLocation location;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pet pet;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<HogwartsJob> positions = new HashSet<>();

    @ManyToMany(mappedBy = "itemOwners", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Item> items = new HashSet<>();

    protected HPCharacter(
    ) {
    }

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

    public Set<HogwartsJob> getPositions() {
        return positions;
    }

    public void setPositions(HogwartsJob hogwartsJob) {
        this.positions.add(hogwartsJob);
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Item item) {
        this.items.add(item);
    }

    public HPLocation getLocation() {
        return location;
    }

    public void setLocation(HPLocation location) {
        this.location = location;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HPCharacter that = (HPCharacter) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate);
    }
}

package harryPotterApp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"imagePath", "location", "pet", "positions", "items"})
@Getter
@Entity
@Table(name = "HPCharacter")
public class HPCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    @Setter
    @NotEmpty
    @Column(nullable = false)
    private String lastName;

    @Setter
    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @Setter
    private String imagePath;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location", foreignKey = @ForeignKey(name = "FK_hpcharacter_location_id"))
    private HPLocation location;

    @Setter
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Pet pet;


    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<HogwartsJob> positions = new HashSet<>();


    @ManyToMany(mappedBy = "itemOwners", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Set<Item> items = new HashSet<>();

    protected HPCharacter(
    ) {
    }

    public HPCharacter(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void setCharactersAsEmployee(HogwartsJob hogwartsJob) {
        this.positions.add(hogwartsJob);
    }

    public void setItems(Item item) {
        this.items.add(item);
    }

}

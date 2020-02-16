package harryPotterApp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@EqualsAndHashCode(exclude = {"imagePath","location","pet","positions","items"})
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "location", foreignKey = @ForeignKey(name = "FK_hpcharacter_location_id"))
    private HPLocation location;

    @Setter
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pet pet;

    @Setter
    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<HogwartsJob> positions = new HashSet<>();

    @Setter
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
}

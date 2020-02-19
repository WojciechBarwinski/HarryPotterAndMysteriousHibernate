package harryPotterApp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@EqualsAndHashCode(exclude = {"imagePath"})
@Entity
@Table(name = "Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "FK_pet_character_id"))
    private HPCharacter owner;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private String species;

    @Setter
    private String imagePath;

    protected Pet() {
    }

    public Pet(String name, HPCharacter owner, String species) {
        this.owner = owner;
        this.name = name;
        this.species = species;
    }
}


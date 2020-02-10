package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "FK_pet_character_id"))
    private HPCharacter owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    protected Pet(){
    }

    public Pet( String name, HPCharacter owner, String species) {
        this.owner = owner;
        this.name = name;
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public HPCharacter getOwner() {
        return owner;
    }

    public void setOwner(HPCharacter owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) &&
                Objects.equals(owner, pet.owner) &&
                Objects.equals(name, pet.name) &&
                Objects.equals(species, pet.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, name, species);
    }
}


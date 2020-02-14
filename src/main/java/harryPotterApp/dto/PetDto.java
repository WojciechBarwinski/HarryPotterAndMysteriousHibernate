package harryPotterApp.dto;

import harryPotterApp.entities.HPCharacter;

public class PetDto {
    private Long id;
    private String name;
    private String species;
    private HPCharacter owner;
    private String imagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public HPCharacter getOwner() {
        return owner;
    }

    public void setOwner(HPCharacter owner) {
        this.owner = owner;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

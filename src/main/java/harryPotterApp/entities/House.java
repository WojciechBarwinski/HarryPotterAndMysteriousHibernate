package harryPotterApp.entities;

public enum House {
    GRYFFINDOR("Gryffindor"),
    RAVENCLAW("Ravenclaw"),
    HUFFLEPUFF("Hufflepuff"),
    SLYTHERIN("Slytherin");

    private String name;

    House(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package entities;

public enum ItemType {
    WAND("Wand"),
    BROOMSTICK("Bromstick"),
    WEAPON("Weapon"),
    ROBE("Robe");

    private String itemName;

    ItemType(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}

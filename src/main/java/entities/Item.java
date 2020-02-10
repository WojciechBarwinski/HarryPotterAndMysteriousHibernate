package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType itemType;

    @Column(unique = true)
    private String itemName;

    @Column(nullable = false)
    private BigDecimal itemValue;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "items_owners", joinColumns = { @JoinColumn(foreignKey = @ForeignKey(name = "FK_item_id")) },
            inverseJoinColumns = { @JoinColumn(foreignKey = @ForeignKey(name = "FK_owner_id")) })
    private Set<HPCharacter> itemOwners = new HashSet<>();

    public Item() {
    }

    public Item(ItemType itemType, String itemName, BigDecimal itemValue) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemValue = itemValue;
    }

    public Long getId() {
        return id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

    public Set<HPCharacter> getItemOwners() {
        return itemOwners;
    }

    public void setItemOwners(HPCharacter itemOwner) {
        this.itemOwners.add(itemOwner);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                itemType == item.itemType &&
                Objects.equals(itemName, item.itemName) &&
                Objects.equals(itemValue, item.itemValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemType, itemName, itemValue);
    }
}

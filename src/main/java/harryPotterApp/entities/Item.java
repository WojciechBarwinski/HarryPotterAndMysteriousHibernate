package harryPotterApp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"itemOwners"})
@Getter
@Entity
@Table(name = "Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType itemType;

    @Setter
    private String itemName;

    @Setter
    @Column(nullable = false)
    private BigDecimal itemValue;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "items_owners", joinColumns = {@JoinColumn(foreignKey = @ForeignKey(name = "FK_item_id"))},
            inverseJoinColumns = {@JoinColumn(foreignKey = @ForeignKey(name = "FK_owner_id"))})
    private Set<HPCharacter> itemOwners = new HashSet<>();

    protected Item() {
    }

    public Item(ItemType itemType, String itemName, BigDecimal itemValue) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemValue = itemValue;
    }

    public void setItemOwners(HPCharacter hpCharacter) {
        this.itemOwners.add(hpCharacter);
    }
}

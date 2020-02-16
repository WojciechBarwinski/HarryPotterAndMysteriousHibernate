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
    private ItemType type;

    @Setter
    private String name;

    @Setter
    @Column(nullable = false)
    private BigDecimal value;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "items_owners", joinColumns = {@JoinColumn(foreignKey = @ForeignKey(name = "FK_item_id"))},
            inverseJoinColumns = {@JoinColumn(foreignKey = @ForeignKey(name = "FK_owner_id"))})
    private Set<HPCharacter> itemOwners = new HashSet<>();

    protected Item() {
    }

    public Item(ItemType type, String name, BigDecimal value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public void setItemOwners(HPCharacter hpCharacter) {
        this.itemOwners.add(hpCharacter);
    }
}

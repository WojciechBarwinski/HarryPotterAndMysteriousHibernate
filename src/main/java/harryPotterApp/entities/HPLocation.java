package harryPotterApp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"residents"})
@Getter
@Entity
@Table(name = "location")
public class HPLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String locationName;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Set<HPCharacter> residents = new HashSet<>();
    @Setter
    private String imagePath;

    protected HPLocation() {
    }

    public HPLocation(String locationName) {
        this.locationName = locationName;
    }

    public void setResidents(HPCharacter hpCharacter) {
        this.residents.add(hpCharacter);
    }
}

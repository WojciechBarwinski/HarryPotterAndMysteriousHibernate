package entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "location")
public class HPLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationName;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HPCharacter> residents;

    public HPLocation() {
    }

    public HPLocation(String locationName) {
        this.locationName = locationName;
    }

    public Long getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<HPCharacter> getResidents() {
        return residents;
    }

    public void setResidents(HPCharacter resident) {
        this.residents.add(resident);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HPLocation that = (HPLocation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(locationName, that.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locationName);
    }
}

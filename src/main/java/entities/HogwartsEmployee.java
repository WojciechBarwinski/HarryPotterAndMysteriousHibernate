package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Hogwards_Employee")
public class HogwartsEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "characters_id")
    private List<HPCharacter> charactersAsEmployee = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private String position;

    public HogwartsEmployee() {
    }

    public HogwartsEmployee(HPCharacter hpCharacter, BigDecimal salary, String position) {
        setCharactersAsEmployee(hpCharacter);
        this.salary = salary;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HPCharacter> getCharactersAsEmployee() {
        return charactersAsEmployee;
    }

    public void setCharactersAsEmployee(HPCharacter hpCharacter) {
        this.charactersAsEmployee.add(hpCharacter);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HogwartsEmployee employee = (HogwartsEmployee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(charactersAsEmployee, employee.charactersAsEmployee) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, charactersAsEmployee, salary, position);
    }
}

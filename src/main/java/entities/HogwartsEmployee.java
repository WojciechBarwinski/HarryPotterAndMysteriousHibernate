package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Hogwards_Employee")
public class HogwartsEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "FK_employee_character_id"))
    private HPCharacter hpCharacter;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private String position;

    public HogwartsEmployee() {
    }

    public HogwartsEmployee(HPCharacter hpCharacter, BigDecimal salary, String position) {
        this.hpCharacter = hpCharacter;
        this.salary = salary;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HPCharacter getHpCharacter() {
        return hpCharacter;
    }

    public void setHpCharacter(HPCharacter hpCharacter) {
        this.hpCharacter = hpCharacter;
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
        HogwartsEmployee that = (HogwartsEmployee) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(hpCharacter, that.hpCharacter) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hpCharacter, salary, position);
    }
}

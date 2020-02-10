package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "Hogwarts_Jobs")
public class HogwartsJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "Characters_Jobs", joinColumns = { @JoinColumn(foreignKey = @ForeignKey(name = "FK_job_id")) },
            inverseJoinColumns = { @JoinColumn(foreignKey = @ForeignKey(name = "FK_employee_id")) })
    private Set<HPCharacter> employees = new HashSet<>();

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private String positionName;

    public HogwartsJob() {
    }

    public HogwartsJob(BigDecimal salary, String positionName) {
        this.salary = salary;
        this.positionName = positionName;
    }

    public Long getId() {
        return id;
    }

    public Set<HPCharacter> getCharactersInJob() {
        return employees;
    }

    public void setCharactersAsEmployee(HPCharacter hpCharacter) {
        this.employees.add(hpCharacter);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HogwartsJob that = (HogwartsJob) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(positionName, that.positionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salary, positionName);
    }
}

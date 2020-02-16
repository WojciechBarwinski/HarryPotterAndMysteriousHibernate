package harryPotterApp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@EqualsAndHashCode(exclude = {"employees"})
@Getter
@Entity
@Table(name = "Hogwarts_Jobs")
public class HogwartsJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "Characters_Jobs", joinColumns = { @JoinColumn(foreignKey = @ForeignKey(name = "FK_job_id")) },
            inverseJoinColumns = { @JoinColumn(foreignKey = @ForeignKey(name = "FK_employee_id")) })
    private Set<HPCharacter> employees = new HashSet<>();

    @Setter
    @Column(nullable = false)
    private BigDecimal salary;

    @Setter
    @Column(nullable = false)
    private String positionName;

    protected HogwartsJob() {
    }

    public HogwartsJob(BigDecimal salary, String positionName) {
        this.salary = salary;
        this.positionName = positionName;
    }
}

package harryPotterApp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@EqualsAndHashCode
@Getter
@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "FK_student_character_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HPCharacter hpCharacter;

    @Setter
    private Integer yearOfStudy;

    @Setter
    @Enumerated(EnumType.STRING)
    private House house;

    protected Student() {

    }

    public Student(HPCharacter hpCharacter, Integer yearOfStudy, House house) {
        this.hpCharacter = hpCharacter;
        this.yearOfStudy = yearOfStudy;
        this.house = house;
    }
}

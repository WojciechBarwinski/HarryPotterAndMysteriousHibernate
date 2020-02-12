package harryPotterApp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "FK_student_character_id"))
    private HPCharacter hpCharacter;
    private Integer yearOfStudy;

    @Enumerated(EnumType.STRING)
    private House house;

    protected Student() {

    }

    public Student(HPCharacter hpCharacter, Integer yearOfStudy, House house) {
        this.hpCharacter = hpCharacter;
        this.yearOfStudy = yearOfStudy;
        this.house = house;
    }

    public Long getId() {
        return id;
    }
    public HPCharacter getHpCharacter() {
        return hpCharacter;
    }

    public void setHpCharacter(HPCharacter hpCharacter) {
        this.hpCharacter = hpCharacter;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(hpCharacter, student.hpCharacter) &&
                Objects.equals(yearOfStudy, student.yearOfStudy) &&
                house == student.house;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hpCharacter, yearOfStudy, house);
    }
}

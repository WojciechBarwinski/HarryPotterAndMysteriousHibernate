package harryPotterApp.repositories;

import harryPotterApp.entities.HogwartsJob;
import harryPotterApp.entities.House;
import harryPotterApp.entities.Student;

import java.util.List;

public interface StudentRepository  {

    Student add(Student student);
    Student findById(Long id);
    Student update(Student student);
    void delete(Student student);
    List<Student> findAllStudentsFromHouse(House house);
    List<Student> findStudentsByLastName(String lastName);
    List <Student> findStudentByIdCharacter(Long id);
}

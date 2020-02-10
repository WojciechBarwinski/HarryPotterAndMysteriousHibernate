package repositories;

import entities.House;
import entities.Student;

import java.util.List;

public interface StudentRepository  {

    Student add(Student student);

    Student findById(Long id);

    Student update(Student student);

    void delete(Student student);

    List<Student> findAllStudentsFromHouse(House house);

    List<Student> findStudentsByLastName(String lastName);
}

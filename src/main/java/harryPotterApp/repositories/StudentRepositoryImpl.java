package harryPotterApp.repositories;

import harryPotterApp.entities.HPLocation;
import harryPotterApp.entities.HogwartsJob;
import harryPotterApp.entities.House;
import harryPotterApp.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class StudentRepositoryImpl implements StudentRepository {
    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student add(Student student) {
        if (Objects.isNull(student.getId())) {
            em.persist(student);
        }
        return student;
    }

    @Override
    public Student findById(Long id) {
        Student student = em.find(Student.class, id);
        return student;
    }

    @Override
    public Student update(Student student) {
        Student mergedStudent = em.merge(student);
        return mergedStudent;
    }

    @Override
    public void delete(Student student) {
        em.remove(student);
    }

    @Override
    public List<Student> findAllStudentsFromHouse(House house) {
        TypedQuery<Student> allStudentsFromHouse = em.createQuery("SELECT s FROM Student s WHERE s.house = :house", Student.class)
                .setParameter("house", house);
        List<Student> resultList = allStudentsFromHouse.getResultList();
        return resultList;
    }

    @Override
    public List<Student> findStudentsByLastName(String lastName) {
        String searchedLastName = lastName;
        Query nativeQuery = em.createNativeQuery("SELECT hpc.lastName, hpc.firstName, s.yearOfStudy, s.house FROM hpcharacter hpc" +
                " INNER JOIN students s ON hpc.id = s.character_id" +
                " WHERE hpc.lastName = ?1").setParameter(1, searchedLastName);
        List <Student> resultList = nativeQuery.getResultList();
        return resultList;
    }

    @Override
    public Student findStudentByIdCharacter(Long id) {
        Long characterId = id;
        Query query = em.createQuery("SELECT s FROM Student as s JOIN s.hpCharacter e where e.id =:id").setParameter("id", characterId);
        List<Student> students= query.getResultList();
        if (query.getResultList().isEmpty()){
            return null;
        }
        return (Student) query.getSingleResult();
    }
}

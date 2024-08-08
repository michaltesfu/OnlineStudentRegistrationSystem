package miu.edu.onlinestudentregistrationsystem.repository;

import miu.edu.onlinestudentregistrationsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
  // List<Student> findByNameContainingIgnoreCase(String query);



   List<Student> findByStudentNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String studentNumber, String firstName, String lastName);


}

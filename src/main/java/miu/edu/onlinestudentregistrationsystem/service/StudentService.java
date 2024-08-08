package miu.edu.onlinestudentregistrationsystem.service;

import miu.edu.onlinestudentregistrationsystem.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student registerStudent(Student student);

    List<Student> findAllStudents();

    void deleteStudent(Long id);

    Optional<Student> findStudentById(Long id);

    List<Student> searchStudents(String query);
}

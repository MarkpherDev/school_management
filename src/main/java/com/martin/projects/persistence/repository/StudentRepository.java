package com.martin.projects.persistence.repository;

import com.martin.projects.persistence.entity.Student;
import com.martin.projects.util.StudentGender;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findByNameContainingIgnoreCase(String name);

  List<Student> findByLastnameContainingIgnoreCase(String lastname);

  List<Student> findByGrade(int grade);

  List<Student> findByGender(StudentGender gender);

  List<Student> findBySchoolId(Long schoold_id);
}

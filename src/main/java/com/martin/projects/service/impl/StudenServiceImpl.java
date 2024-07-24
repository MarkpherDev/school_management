package com.martin.projects.service.impl;

import com.martin.projects.dto.request.SaveStudent;
import com.martin.projects.dto.response.StudentDto;
import com.martin.projects.exception.ObjectNotFoundException;
import com.martin.projects.mapper.StudenMapper;
import com.martin.projects.persistence.entity.School;
import com.martin.projects.persistence.entity.Student;
import com.martin.projects.persistence.repository.SchoolRepository;
import com.martin.projects.persistence.repository.StudentRepository;
import com.martin.projects.service.StudentService;
import com.martin.projects.util.StudentGender;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudenServiceImpl implements StudentService {


  private final StudentRepository studentRepository;
  private final SchoolRepository schoolRepository;

  @Autowired
  public StudenServiceImpl(StudentRepository studentRepository, SchoolRepository schoolRepository) {
    this.studentRepository = studentRepository;
    this.schoolRepository = schoolRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<StudentDto> findAll() {
    return StudenMapper.toStudentDtoList(studentRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<StudentDto> findByName(String name) {
    List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
    return StudenMapper.toStudentDtoList(students);
  }

  @Transactional(readOnly = true)
  @Override
  public List<StudentDto> findByLastname(String lastname) {
    List<Student> students = studentRepository.findByLastnameContainingIgnoreCase(lastname);
    return StudenMapper.toStudentDtoList(students);
  }

  @Transactional(readOnly = true)
  @Override
  public List<StudentDto> findByGrade(int grade) {
    List<Student> students = studentRepository.findByGrade(grade);
    return StudenMapper.toStudentDtoList(students);
  }

  @Transactional(readOnly = true)
  @Override
  public List<StudentDto> findByGender(StudentGender gender) {
    List<Student> students = studentRepository.findByGender(gender);
    return StudenMapper.toStudentDtoList(students);
  }

  @Transactional(readOnly = true)
  @Override
  public List<StudentDto> findBySchool(Long id) {
    List<Student> students = studentRepository.findBySchoolId(id);
    return StudenMapper.toStudentDtoList(students);
  }

  @Transactional(readOnly = true)
  @Override
  public StudentDto findById(Long id) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Student with id " + id + " not found "));
    return StudenMapper.toStudentDto(student);
  }

  @Override
  public StudentDto createStudent(SaveStudent student) {
    School schoolExists = schoolRepository.findById(student.getSchoolId())
        .orElseThrow(
            () -> new ObjectNotFoundException("School wit id " + student.getSchoolId() + " "
                + "not found."));
    Student newStudent = StudenMapper.toStudentEntity(student);
    newStudent.setSchool(schoolExists);
    return StudenMapper.toStudentDto(studentRepository.save(newStudent));
  }

  @Override
  public StudentDto updateStudent(Long id, SaveStudent student) {
    Student oldStudent = studentRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Student with id " + id + " not found "));
    School schoolExists = schoolRepository.findById(student.getSchoolId())
        .orElseThrow(
            () -> new ObjectNotFoundException("School wit id " + student.getSchoolId() + " "
                + "not found."));
    StudenMapper.updateStudentEntity(oldStudent, student);
    oldStudent.setSchool(schoolExists);
    return StudenMapper.toStudentDto(studentRepository.save(oldStudent));
  }

  @Override
  public void deleteStudent(Long id) {
    Student studentExists = studentRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Student with id " + id + " not found "));
    studentRepository.delete(studentExists);
  }
}

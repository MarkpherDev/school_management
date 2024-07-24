package com.martin.projects.service;

import com.martin.projects.dto.request.SaveStudent;
import com.martin.projects.dto.response.StudentDto;
import com.martin.projects.util.StudentGender;
import java.util.List;

public interface SchoolService {

  List<StudentDto> findAll();

  List<StudentDto> findByName(String name);

  List<StudentDto> findByLastname(String lastname);

  List<StudentDto> findByGrade(int grade);

  List<StudentDto> findByGender(StudentGender gender);

  List<StudentDto> findBySchool(Long id);

  StudentDto findById(Long id);

  StudentDto createStudent(SaveStudent student);

  StudentDto updateStudent(Long id, SaveStudent student);

  void deleteStudent(Long id);
}

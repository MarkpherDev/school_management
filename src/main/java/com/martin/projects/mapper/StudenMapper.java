package com.martin.projects.mapper;

import com.martin.projects.dto.request.SaveStudent;
import com.martin.projects.dto.response.StudentDto;
import com.martin.projects.persistence.entity.Student;
import java.util.List;

public class StudenMapper {

  public static StudentDto toStudentDto(Student student) {
    if (student == null) {
      return null;
    }

    return new StudentDto(
        student.getId(),
        student.getName(),
        student.getLastname(),
        student.getBirthday(),
        student.getGender(),
        student.getGrade(),
        student.getCreatedAt(),
        SchoolMapper.toSchoolDto(student.getSchool())
    );
  }

  public static List<StudentDto> toStudentDtoList(List<Student> students) {
    if (students == null) {
      return null;
    }

    return students.stream()
        .map(StudenMapper::toStudentDto)
        .toList();
  }

  public static Student toStudentEntity(SaveStudent studentDto) {
    if (studentDto == null) {
      return null;
    }

    Student student = new Student();
    student.setName(studentDto.getName());
    student.setLastname(studentDto.getLastname());
    student.setBirthday(studentDto.getBirthday());
    student.setGender(studentDto.getGender());
    student.setGrade(studentDto.getGrade());

    return student;
  }


  public static void updateStudentEntity(Student oldStudent, SaveStudent studentDto) {
    if (oldStudent == null || studentDto == null) {
      return;
    }

    oldStudent.setName(studentDto.getName());
    oldStudent.setLastname(studentDto.getLastname());
    oldStudent.setBirthday(studentDto.getBirthday());
    oldStudent.setGender(studentDto.getGender());
    oldStudent.setGrade(studentDto.getGrade());
  }
}

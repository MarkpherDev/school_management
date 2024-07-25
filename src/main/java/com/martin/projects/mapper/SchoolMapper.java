package com.martin.projects.mapper;

import com.martin.projects.dto.request.SaveSchool;
import com.martin.projects.dto.response.SchoolDto;
import com.martin.projects.persistence.entity.School;
import com.martin.projects.persistence.entity.Student;
import java.util.List;

public class SchoolMapper {

  public static SchoolDto toSchoolDto(School school) {
    if (school == null) {
      return null;
    }

    return new SchoolDto(
        school.getId(),
        school.getName(),
        school.getEmail(),
        school.getAddress(),
        school.getCity(),
        school.getState(),
        school.getPostalCode(),
        school.getPhone(),
        school.getCreatedAt(),
        school.getStudents().stream()
            .map(Student::getId).toList()
    );
  }

  public static List<SchoolDto> toSchoolDtoList(List<School> schools) {
    if (schools == null) {
      return null;
    }

    return schools.stream()
        .map(SchoolMapper::toSchoolDto)
        .toList();
  }

  public static School toSchoolEntity(SaveSchool schoolDto) {
    if (schoolDto == null) {
      return null;
    }

    School school = new School();
    school.setName(schoolDto.getName());
    school.setEmail(schoolDto.getEmail());
    school.setAddress(schoolDto.getAddress());
    school.setCity(schoolDto.getCity());
    school.setState(schoolDto.getState());
    school.setPostalCode(schoolDto.getPostalCode());
    school.setPhone(schoolDto.getPhone());

    return school;
  }

  public static void updateSchoolEntity(School oldSchool, SaveSchool schoolDto) {
    if (oldSchool == null || schoolDto == null) {
      return;
    }

    oldSchool.setName(schoolDto.getName());
    oldSchool.setEmail(schoolDto.getEmail());
    oldSchool.setAddress(schoolDto.getAddress());
    oldSchool.setCity(schoolDto.getCity());
    oldSchool.setState(schoolDto.getState());
    oldSchool.setPostalCode(schoolDto.getPostalCode());
    oldSchool.setPhone(schoolDto.getPhone());
  }
}

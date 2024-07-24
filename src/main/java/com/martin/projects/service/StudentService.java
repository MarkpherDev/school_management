package com.martin.projects.service;

import com.martin.projects.dto.request.SaveSchool;
import com.martin.projects.dto.response.SchoolDto;
import java.util.List;

public interface StudentService {

  List<SchoolDto> findAll();

  List<SchoolDto> findByName(String name);

  List<SchoolDto> findByCity(String city);

  List<SchoolDto> findByState(String state);

  List<SchoolDto> findByPostalCode(String postalCode);

  SchoolDto findById(Long id);

  SchoolDto createSchool(SaveSchool student);

  SchoolDto updateSchool(Long id, SaveSchool student);

  void deleteSchool(Long id);

}

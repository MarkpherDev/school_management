package com.martin.projects.service.impl;

import com.martin.projects.dto.request.SaveSchool;
import com.martin.projects.dto.response.SchoolDto;
import com.martin.projects.exception.ObjectNotFoundException;
import com.martin.projects.mapper.SchoolMapper;
import com.martin.projects.persistence.entity.School;
import com.martin.projects.persistence.repository.SchoolRepository;
import com.martin.projects.service.SchoolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

  private final SchoolRepository schoolRepository;

  @Autowired
  public SchoolServiceImpl(SchoolRepository schoolRepository) {
    this.schoolRepository = schoolRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<SchoolDto> findAll() {
    return SchoolMapper.toSchoolDtoList(schoolRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<SchoolDto> findByName(String name) {
    List<School> schools = schoolRepository.findByNameContainingIgnoreCase(name);
    return SchoolMapper.toSchoolDtoList(schools);
  }

  @Transactional(readOnly = true)
  @Override
  public List<SchoolDto> findByCity(String city) {
    List<School> schools = schoolRepository.findByCityContainingIgnoreCase(city);
    return SchoolMapper.toSchoolDtoList(schools);
  }

  @Transactional(readOnly = true)
  @Override
  public List<SchoolDto> findByState(String state) {
    List<School> schools = schoolRepository.findByStateContainingIgnoreCase(state);
    return SchoolMapper.toSchoolDtoList(schools);
  }

  @Transactional(readOnly = true)
  @Override
  public List<SchoolDto> findByPostalCode(String postalCode) {
    List<School> schools = schoolRepository.findByPostalCodeContainingIgnoreCase(postalCode);
    return SchoolMapper.toSchoolDtoList(schools);
  }

  @Transactional(readOnly = true)
  @Override
  public SchoolDto findById(Long id) {
    School schoolExists = schoolRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("School wit id " + id + " not found"));
    return SchoolMapper.toSchoolDto(schoolExists);
  }

  @Override
  public SchoolDto createSchool(SaveSchool school) {
    School newSchool = SchoolMapper.toSchoolEntity(school);
    return SchoolMapper.toSchoolDto(schoolRepository.save(newSchool));
  }

  @Override
  public SchoolDto updateSchool(Long id, SaveSchool school) {
    School oldSchool = schoolRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("School wit id " + id + " not found"));
    SchoolMapper.updateSchoolEntity(oldSchool, school);
    return SchoolMapper.toSchoolDto(schoolRepository.save(oldSchool));
  }

  @Override
  public void deleteSchool(Long id) {
    School schoolExists = schoolRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("School wit id " + id + " not found"));
    schoolRepository.delete(schoolExists);
  }
}

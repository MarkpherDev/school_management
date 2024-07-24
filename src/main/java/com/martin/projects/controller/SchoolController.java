package com.martin.projects.controller;

import com.martin.projects.dto.request.SaveSchool;
import com.martin.projects.dto.response.SchoolDto;
import com.martin.projects.service.SchoolService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {

  private final SchoolService schoolService;

  @Autowired
  public SchoolController(SchoolService schoolService) {
    this.schoolService = schoolService;
  }

  @GetMapping
  public ResponseEntity<List<SchoolDto>> findAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String city,
      @RequestParam(required = false) String state,
      @RequestParam(required = false) String postalCode) {

    List<SchoolDto> schools;

    if (StringUtils.hasText(name)) {
      schools = schoolService.findByName(name);
    } else if (StringUtils.hasText(city)) {
      schools = schoolService.findByCity(city);
    } else if (StringUtils.hasText(state)) {
      schools = schoolService.findByState(state);
    } else if (StringUtils.hasText(postalCode)) {
      schools = schoolService.findByPostalCode(postalCode);
    } else {
      schools = schoolService.findAll();
    }

    return ResponseEntity.ok(schools);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SchoolDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(schoolService.findById(id));
  }

  @PostMapping
  public ResponseEntity<SchoolDto> createSchool(@RequestBody @Valid SaveSchool schoolDto,
      HttpServletRequest request) {
    SchoolDto schoolCreated = schoolService.createSchool(schoolDto);
    String baseUrl = request.getRequestURL().toString();
    URI newLocation = URI.create(baseUrl + "/" + schoolCreated.getId());
    return ResponseEntity.created(newLocation).body(schoolCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SchoolDto> updateSchool(@PathVariable("id") Long id,
      @RequestBody @Valid SaveSchool schoolDto) {
    SchoolDto updatedSchool = schoolService.updateSchool(id, schoolDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedSchool);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSchool(@PathVariable("id") Long id) {
    schoolService.deleteSchool(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Escuela Eliminada Satisfactoriamente");
  }
}

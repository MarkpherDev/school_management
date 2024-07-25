package com.martin.projects.controller;

import com.martin.projects.dto.request.SaveStudent;
import com.martin.projects.dto.response.StudentDto;
import com.martin.projects.service.StudentService;
import com.martin.projects.util.StudentGender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/student")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<List<StudentDto>> findAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String lastname,
      @RequestParam(required = false) StudentGender gender,
      @RequestParam(required = false) String grade) {

    List<StudentDto> students;

    if (StringUtils.hasText(name)) {
      students = studentService.findByName(name);
    } else if (StringUtils.hasText(lastname)) {
      students = studentService.findByLastname(lastname);
    } else if (gender != null) {
      students = studentService.findByGender(gender);
    } else if (StringUtils.hasText(grade)) {
      students = studentService.findByGrade(Integer.parseInt(grade));
    } else {
      students = studentService.findAll();
    }

    return ResponseEntity.ok(students);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid SaveStudent studentDto,
      HttpServletRequest request) {
    StudentDto studentCreated = studentService.createStudent(studentDto);
    String baseUrl = request.getRequestURL().toString();
    URI newLocation = URI.create(baseUrl + "/" + studentCreated.getId());
    return ResponseEntity.created(newLocation).body(studentCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long id,
      @RequestBody @Valid SaveStudent studentDto) {
    StudentDto studentUpdated = studentService.updateStudent(id, studentDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentUpdated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable("id") Long id) {
    studentService.deleteStudent(id);
    Map<String, String> message = new HashMap<>();
    message.put("message", "Estudiante eliminado correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
  }
}

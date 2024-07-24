package com.martin.projects.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class SchoolDto {

  private String name;
  private String email;
  private String address;
  private String city;
  private String state;
  private String postalCode;
  private String phone;
  private LocalDateTime createdAt;
  private List<StudentDto> students;
}

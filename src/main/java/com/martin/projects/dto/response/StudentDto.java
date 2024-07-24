package com.martin.projects.dto.response;

import com.martin.projects.util.StudentGender;
import java.time.LocalDateTime;
import java.util.Date;

public class StudentDto {

  private String name;
  private String lastname;
  private Date birthday;
  private StudentGender gender;
  private int grade;
  private LocalDateTime createdAt;
  private SchoolDto schoolDto;
}

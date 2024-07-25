package com.martin.projects.dto.response;

import com.martin.projects.util.StudentGender;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

  private Long id;
  private String name;
  private String lastname;
  private Date birthday;
  private StudentGender gender;
  private int grade;
  private LocalDateTime createdAt;
  private Long schoolId;
}

package com.martin.projects.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolDto {

  private Long id;
  private String name;
  private String email;
  private String address;
  private String city;
  private String state;
  @JsonProperty("postal_code")
  private String postalCode;
  private String phone;
  private LocalDateTime createdAt;
  private List<StudentDto> students;
}

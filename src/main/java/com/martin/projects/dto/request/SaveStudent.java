package com.martin.projects.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.projects.util.StudentGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveStudent {

  @NotBlank(message = "El campo name no puede ir vacio")
  @Size(min = 2, max = 30, message = "El campo name debe tener entre 2 y 30 caracteres")
  private String name;

  @NotBlank(message = "El campo lastname no puede ir vacio")
  @Size(min = 2, max = 30, message = "El campo lastname debe tener entre 2 y 30 caracteres")
  private String lastname;

  @NotNull(message = "El campo birthday es obligatorio")
  @Past(message = "El campo birthday debe ser en el pasado")
  private Date birthday;

  @NotNull(message = "El campo gender no puede ir vacio")
  @Enumerated(EnumType.STRING)
  private StudentGender gender;

  @NotNull(message = "El campo grade es obligatorio")
  @Min(value = 1, message = "El minimo valor es 1")
  @Max(value = 6, message = "el maximo valor es 6")
  private int grade;

  @JsonProperty(value = "school_id")
  @NotNull(message = "El campo school_id no puede ir vacio")
  private Long schoolId;
}

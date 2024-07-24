package com.martin.projects.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveSchool {

  @NotBlank(message = "El campo name no puede ir vacio")
  @Size(min = 2, max = 100, message = "el campo name debe tener entre 2 y 100 caracteres")
  private String name;

  @NotBlank(message = "El campo email no puede ir vacio")
  @Email(message = "el campo email debe tener un formato valido")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|hotmail\\.com)$", message = "el campo email "
      + "debe terminar en @gmail.com o @hotmail.com")
  private String email;

  @NotBlank(message = "El campo address no puede ir vacio")
  @Size(min = 5, max = 100, message = "el campo address debe tener entre 5 y 100 caracteres")
  private String address;

  @NotBlank(message = "El campo city no puede ir vacio")
  @Size(min = 2, max = 50, message = "el campo city debe tener entre 5 y 50 caracteres")
  private String city;

  @NotBlank(message = "El campo state no puede ir vacio")
  @Size(min = 2, max = 50, message = "el campo address debe tener entre 5 y 50 caracteres")
  private String state;

  @NotBlank(message = "El campo postal_code no puede ir vacio")
  @JsonProperty(value = "postal_code")
  @Pattern(regexp = "\\d{5}", message = "el campo postal_code debe tener 5 digitos")
  private String postalCode;

  @NotBlank(message = "El campo phone no puede ir vacio")
  @Pattern(regexp = "\\d{9}", message = "el campo phone debe tener 9 digitos")
  private String phone;
}

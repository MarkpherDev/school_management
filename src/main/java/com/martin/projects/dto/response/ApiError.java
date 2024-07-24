package com.martin.projects.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

  @JsonProperty("http_code")
  private int httpCode;

  private String url;

  @JsonProperty("http_method")
  private String httpMethod;

  private String message;

  @JsonProperty("backend_message")
  private String backendMessage;

  private LocalDateTime timestamp;

  private List<String> details;
}

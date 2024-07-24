package com.martin.projects.exception;

import com.martin.projects.dto.response.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  ZoneId zoneId = ZoneId.of("America/Lima");
  LocalDateTime timestamp = LocalDateTime.now(zoneId);

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<ApiError> handleObjectNotFoundException(
      ObjectNotFoundException objectNotFoundException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.NOT_FOUND.value();

    ApiError apiError = new ApiError(
        httpStatus,
        request.getRequestURL().toString(),
        request.getMethod(),
        "La informacion requerida no existe, " +
            "Por favor revise la URL o intente otra busqueda.",
        objectNotFoundException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiError);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException httpMessageNotReadableException, HttpServletRequest request) {
    int httpStatus = HttpStatus.BAD_REQUEST.value();

    ApiError apiError = new ApiError(
        httpStatus,
        request.getRequestURL().toString(),
        request.getMethod(),
        "Error en la lectura del HTTP message body, Compruebe que " +
            "formato es el correcto y contenga data valida",
        httpMessageNotReadableException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiError);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ApiError> handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();

    ApiError apiError = new ApiError(
        httpStatus,
        request.getRequestURL().toString(),
        request.getMethod(),
        "Unsupported Media Type, Supported Media Types son: "
            + httpMediaTypeNotSupportedException.getSupportedMediaTypes() + " y tu enviaste: "
            + httpMediaTypeNotSupportedException.getContentType(),
        httpMediaTypeNotSupportedException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiError);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();

    ApiError apiError = new ApiError(
        httpStatus,
        request.getRequestURL().toString(),
        request.getMethod(),
        "Metodo no permitido. Revisa el metodo HTTP de tu request.",
        httpRequestMethodNotSupportedException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiError);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
    List<String> details = errors.stream()
        .map(error -> {
          if (error instanceof FieldError fieldError) {
            return fieldError.getField() + ": " + fieldError.getDefaultMessage();
          }

          return error.getDefaultMessage();
        }).toList();

    ApiError apiError = new ApiError(
        httpStatus,
        request.getRequestURL().toString(),
        request.getMethod(),
        "La request tiene parametros invalidos o incompletos. " +
            "Verifique la informacion requerida e intentelo otra vez.",
        methodArgumentNotValidException.getMessage(),
        timestamp,
        details
    );

    return ResponseEntity.status(httpStatus).body(apiError);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException methodArgumentTypeMismatchException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    Object valueRejected = methodArgumentTypeMismatchException.getValue();
    String propertyName = methodArgumentTypeMismatchException.getName();

    ApiError apiError = new ApiError(
        httpStatus,
        request.getRequestURL().toString(),
        request.getMethod(),
        "Request Invalido: el valor proporcionado " + valueRejected
            + " no tiene el type esperado " + "para el " + propertyName,
        methodArgumentTypeMismatchException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiError);
  }
  
}

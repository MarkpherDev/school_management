package com.martin.projects.exception;


public class ObjectNotFoundException extends RuntimeException {

  private final String message;
  private final Throwable cause;

  public ObjectNotFoundException(String message) {
    this.message = message;
    this.cause = null;
  }

  public ObjectNotFoundException(String message, Throwable cause) {
    this.message = message;
    this.cause = cause;
  }

  @Override
  public String toString() {
    String message = super.getMessage();

    if (message == null) {
      message = "";
    }

    return message
        .concat("Object not found: ")
        .concat(this.message);
  }

  public String getObjectNotFoundName() {
    return this.message;
  }
}

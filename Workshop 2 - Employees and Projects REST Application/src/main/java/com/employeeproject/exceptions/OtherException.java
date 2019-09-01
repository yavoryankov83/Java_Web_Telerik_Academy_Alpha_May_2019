package com.employeeproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class OtherException extends IllegalArgumentException {

  public OtherException(String message) {
    super(message);
  }
}

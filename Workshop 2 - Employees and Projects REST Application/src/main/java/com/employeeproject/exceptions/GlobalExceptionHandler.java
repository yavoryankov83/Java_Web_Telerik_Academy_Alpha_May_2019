package com.employeeproject.exceptions;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ConditionalOnProperty(value="exception.handler.enabled", havingValue = "true")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class})
  protected ResponseEntity<Object> handleException(IllegalArgumentException ex, WebRequest request) {
    if (ex instanceof NotFoundException) {
      return handleNotFoundException((NotFoundException) ex, request);
    }
    if (ex instanceof ConflictException) {
      return handleConflictException((ConflictException) ex, request);
    }
    if (ex instanceof BadRequestException) {
      return handleBadRequestException((BadRequestException) ex, request);
    }
    return handleOtherException((OtherException) ex, request);
  }

  private ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  private ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  private ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  private ResponseEntity<Object> handleOtherException(OtherException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
  }
}

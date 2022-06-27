package com.reactive.spring.mysql.exception.handler;

import com.reactive.spring.mysql.exception.bean.CustomerExceptionBean;
import com.reactive.spring.mysql.exception.types.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<CustomerExceptionBean> genericException(RuntimeException re) {
    log.error("RuntimeException occurred ", re);

    return new ResponseEntity(
        CustomerExceptionBean.builder()
            .timestamp(Timestamp.from(Instant.now()))
            .message(re.getMessage())
            .errorCode("ERR-999")
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomerException.class)
  public ResponseEntity<CustomerExceptionBean> customException(CustomerException ce) {
    log.error("CustomerException occurred ", ce);

    return new ResponseEntity(
        CustomerExceptionBean.builder()
            .timestamp(Timestamp.from(Instant.now()))
            .message(ce.getMessage())
            .errorCode(ce.getErrorCode())
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<CustomerExceptionBean> validationException(WebExchangeBindException wbe) {
    log.error("WebExchangeBindException occurred ", wbe);

    List<Map<String, String>> errors = new ArrayList<>();
    wbe.getFieldErrors()
        .forEach(
            fieldError -> {
              Map<String, String> error = new HashMap<>();
              error.put("field", fieldError.getField());
              error.put("fieldError", fieldError.getDefaultMessage());
              errors.add(error);
            });

    return new ResponseEntity(
        CustomerExceptionBean.builder()
            .timestamp(Timestamp.from(Instant.now()))
            .errors(errors)
            .errorCode("ERR-400")
            .build(),
        HttpStatus.BAD_REQUEST);
  }
}

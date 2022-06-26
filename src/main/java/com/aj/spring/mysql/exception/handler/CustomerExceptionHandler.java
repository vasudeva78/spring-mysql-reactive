package com.aj.spring.mysql.exception.handler;

import com.aj.spring.mysql.exception.bean.CustomerExceptionBean;
import com.aj.spring.mysql.exception.types.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<CustomerExceptionBean> genericException(RuntimeException re) {
    log.error("Exception occurred ", re);
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
    log.error("Exception occurred ", ce);
    return new ResponseEntity(
        CustomerExceptionBean.builder()
            .timestamp(Timestamp.from(Instant.now()))
            .message(ce.getMessage())
            .errorCode(ce.getErrorCode())
            .build(),
        HttpStatus.BAD_REQUEST);
  }
}

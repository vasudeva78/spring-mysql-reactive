package com.aj.spring.mysql.exception.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerExceptionBean {
  private Timestamp timestamp;
  private String errorCode;
  private String message;
  private String correlationid;
}

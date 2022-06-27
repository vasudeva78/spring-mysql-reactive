package com.reactive.spring.mysql.exception.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
  private List<Map<String, String>> errors;
}

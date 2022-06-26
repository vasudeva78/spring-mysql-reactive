package com.aj.spring.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.aj.spring.mysql")
public class MysqlApplication {

  public static void main(String[] args) {
    SpringApplication.run(MysqlApplication.class, args);
  }
}

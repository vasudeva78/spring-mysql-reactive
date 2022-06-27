package com.reactive.spring.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.reactive.spring.mysql")
public class MysqlReactiveApplication {

  public static void main(String[] args) {
    SpringApplication.run(MysqlReactiveApplication.class, args);
  }
}

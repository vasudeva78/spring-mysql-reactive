package com.reactive.spring.mysql.controller;

import com.reactive.spring.mysql.entity.Customer;
import com.reactive.spring.mysql.exception.types.CustomerException;
import com.reactive.spring.mysql.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class CustomerController {

  @Autowired private CustomerService customerService;

  @GetMapping("/customers")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Customer> allCustomers() {
    return customerService.getAllCustomers();
  }

  @PostMapping(value = "/customers", consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Customer> saveCustomer(@Valid @RequestBody Customer customerRequest) {
    return customerService
        .saveCustomer(customerRequest)
        .onErrorMap(
            exception ->
                CustomerException.builder()
                    .message("Error in saving customer data")
                    .errorCode("ERR-102")
                    .build());
  }
}

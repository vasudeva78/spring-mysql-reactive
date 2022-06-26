package com.aj.spring.mysql.service;

import com.aj.spring.mysql.entity.Customer;
import com.aj.spring.mysql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class CustomerService {
  @Autowired private CustomerRepository customerRepository;

  private Scheduler databaseTasks =
      Schedulers.newBoundedElastic(100, 100, "mysql-database-threadpool");

  public Flux<Customer> getAllCustomers() {
    return this.customerRepository.findAll().publishOn(databaseTasks);
  }

  public Mono<Customer> saveCustomer(Customer customer) {
    return this.customerRepository
        .save(customer)
        .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)))
        .publishOn(databaseTasks);
  }
}

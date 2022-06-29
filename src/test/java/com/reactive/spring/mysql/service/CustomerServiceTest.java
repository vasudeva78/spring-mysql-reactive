package com.reactive.spring.mysql.service;

import com.reactive.spring.mysql.entity.Customer;
import com.reactive.spring.mysql.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
// @ExtendWith(SpringExtension.class)
class CustomerServiceTest {

  @Mock private CustomerRepository customerRepository;

  @InjectMocks private CustomerService customerService = new CustomerService();

  @Test
  void getAllCustomers_when_no_errors() {
    when(customerRepository.findAll())
        .thenReturn(
            Flux.just(
                new Customer(1, "name1", "address1", "country1"),
                new Customer(2, "name2", "address2", "country2")));

    StepVerifier.create(customerService.getAllCustomers())
        .expectNextMatches(customer1 -> customer1.getName().equals("name1"))
        .expectNextMatches(customer2 -> customer2.getName().equals("name2"))
        .expectComplete()
        .verify();
  }

  @Test
  void saveCustomer_when_no_error() {
    Customer customer = new Customer();
    when(customerRepository.save(customer))
        .thenReturn(Mono.just(new Customer(1, "name1", "address1", "country1")));

    StepVerifier.create(customerService.saveCustomer(customer))
        .expectNextMatches(customer1 -> customer1.getName().equals("name1"))
        .verifyComplete();
  }
}

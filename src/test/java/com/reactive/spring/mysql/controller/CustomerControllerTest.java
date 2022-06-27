package com.reactive.spring.mysql.controller;

import com.reactive.spring.mysql.entity.Customer;
import com.reactive.spring.mysql.exception.bean.CustomerExceptionBean;
import com.reactive.spring.mysql.exception.types.CustomerException;
import com.reactive.spring.mysql.repository.CustomerRepository;
import com.reactive.spring.mysql.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerController.class)
@Import(CustomerService.class)
class CustomerControllerTest {

  @MockBean CustomerRepository repository;
  @Autowired private WebTestClient webClient;

  @Test
  void getAllCustomers_when_at_least_one_customer() {
    Customer customer = new Customer(1, "name1", "address1", "country1");

    when(repository.findAll()).thenReturn(Flux.just(customer));

    webClient
        .get()
        .uri("/customers", "Test")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(Customer.class)
        .hasSize(1);
  }

  @Test
  void getAllCustomers_when_no_customer() {
    when(repository.findAll()).thenReturn(Flux.empty());

    webClient
        .get()
        .uri("/customers", "Test")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(Customer.class)
        .hasSize(0);
  }

  @Test
  void getAllCustomers_when_error() {
    when(repository.findAll()).thenReturn(Flux.error(new RuntimeException()));

    webClient
        .get()
        .uri("/customers", "Test")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBodyList(CustomerExceptionBean.class)
        .hasSize(1);
  }

  @Test
  void saveCustomer_when_no_error() {
    Customer customer = new Customer(1, "name1", "address1", "country1");

    Customer customerRequest = new Customer(1, "name1", "address1", "country1");
    when(repository.save(customerRequest)).thenReturn(Mono.just(customer));

    webClient
        .post()
        .uri("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(customerRequest))
        .exchange()
        .expectStatus()
        .isCreated();
  }

  @Test
  void saveCustomer_when_request_validation_error() {
    Customer customer = new Customer(1, "name1", "address1", "country1");

    Customer customerRequest = new Customer(1, "", "address1", "country1");
    when(repository.save(customerRequest)).thenReturn(Mono.just(customer));

    webClient
        .post()
        .uri("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(customerRequest))
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void saveCustomer_when_error() {
    Customer customerRequest = new Customer(1, "name1", "address1", "country1");
    when(repository.save(customerRequest))
        .thenReturn(
            Mono.error(
                CustomerException.builder()
                    .message("Error in saving customer data")
                    .errorCode("ERR-102")
                    .build()));

    webClient
        .post()
        .uri("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(customerRequest))
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBodyList(CustomerExceptionBean.class);
  }
}

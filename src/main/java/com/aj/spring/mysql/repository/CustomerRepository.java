package com.aj.spring.mysql.repository;

import com.aj.spring.mysql.entity.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CustomerRepository extends R2dbcRepository<Customer, Integer> {}

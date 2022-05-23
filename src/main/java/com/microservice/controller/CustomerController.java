package com.microservice.controller;

import com.microservice.model.Customer;
import com.microservice.service.CustomerService;
import com.microservice.service.impl.CustomerServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService service;

    private static final String CUSTOMER = "customer";

    @GetMapping(value = "/getAllCustomers")
    public Flux<Customer> getAllCustomers() {
        return service.customerGetAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Customer> getByIdCustomer(@PathVariable String id) {
        return service.customerGetById(id);
    }

    @PostMapping(value = "/create")
    @CircuitBreaker(name = CUSTOMER, fallbackMethod = "customer")
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return service.customerCreate(customer);
    }

    @PutMapping(value = "/update/{id}")
    @CircuitBreaker(name = CUSTOMER, fallbackMethod = "customer")
    public Mono<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        return service.customerUpdate(id, customer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id) {
        return service.customerDelete(id);
    }
}

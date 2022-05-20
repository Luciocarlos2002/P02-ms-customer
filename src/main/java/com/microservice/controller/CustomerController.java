package com.microservice.controller;

import com.microservice.model.Customer;
import com.microservice.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerServiceImpl service;

    @GetMapping(value = "/getAllCustomers")
    public Flux<Customer> getAll() {
        return service.customerGetAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Customer> getOne(@PathVariable String id) {
        return service.customerGetById(id);
    }

    @PostMapping(value = "/create")
    public Mono<Customer> create(@RequestBody Customer customer) {
        return service.customerCreate(customer);
    }

    @PutMapping(value = "/update/{id}")
    public Mono<Customer> update(@PathVariable String id, @RequestBody Customer customer) {
        return service.customerUpdate(id, customer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.customerDelete(id);
    }
}

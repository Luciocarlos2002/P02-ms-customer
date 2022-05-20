package com.microservice.service.impl;

import com.microservice.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerServiceImpl {

    Flux<Customer> customerGetAll();

    Mono<Customer> customerGetById(String id);

    Mono<Customer> customerCreate(Customer customer);

    Mono<Customer> customerUpdate(String id, Customer customer);

    Mono<Void> customerDelete(String id);

}

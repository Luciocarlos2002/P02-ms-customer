package com.microservice.service;

import com.microservice.model.Customer;
import com.microservice.repository.CustomerRepository;
import com.microservice.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceImpl {

    private final CustomerRepository customerRepository;

    @Override
    public Flux<Customer> customerGetAll(){
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> customerGetById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> customerCreate(Customer customer) {
        String typeCustomer = customer.getTypeCustomer();
        switch (typeCustomer){
            case "Personal":
                customer.setTypeProfile("VIP");
                break;
            case "Empresa":
                customer.setTypeProfile("PYME");
                break;
        }
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> customerUpdate(String id, Customer customer) {
        String typeCustomer = customer.getTypeCustomer();
        switch (typeCustomer){
            case "Personal":
                customer.setTypeProfile("VIP");
                break;
            case "Empresa":
                customer.setTypeProfile("PYME");
                break;
        }
        return customerRepository.findById(id).flatMap(newCustomer -> {
            newCustomer.setFullName(customer.getFullName());
            newCustomer.setNumberIdentity(customer.getNumberIdentity());
            newCustomer.setTypeCustomer(customer.getTypeCustomer());
            newCustomer.setTypeProfile(customer.getTypeProfile());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());
            newCustomer.setEmailCustomer(customer.getEmailCustomer());
            return customerRepository.save(newCustomer);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> customerDelete(String id) {
        return customerRepository.findById(id).flatMap(customer -> customerRepository.deleteById(customer.getId()));
    }

}

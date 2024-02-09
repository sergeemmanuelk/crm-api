package com.crm.apidemo.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer Not Found!"));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setLastName(customer.getLastName());
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setContact(customer.getContact());
                    existingCustomer.setAddress(customer.getAddress());
                    return customerRepository.save(existingCustomer);
                }).orElseThrow(()-> new RuntimeException("Customer Not Found!"));
    }

    @Override
    public void deleteCustomer(Long id) {
        // check
        // customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer Not Found!"));
        // delete
        customerRepository.deleteById(id);
    }
}

package com.financialservices.controllers;

import com.financialservices.exception.ResourceNotFoundException;
import com.financialservices.repository.CustomerRepository;
import com.financialservices.models.Customer;
import com.financialservices.models.User;
import com.financialservices.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;
    
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/customers")
    public List<Customer> getCustomer() {
        return customerRepo.findAll();
    }
  

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
        return ResponseEntity.ok(customer);
    }
    
    
     @PostMapping("/users/{userId}/customer")
    public Customer createCustomer(@PathVariable(value = "userId") Long userId,
           @RequestBody Customer customer) {
        User user = userRepo.findById(userId)
                 .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + userId));
        customer.setUser(user);
        return customerRepo.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));

        customer.setAddress(customerDetails.getAddress());
        customer.setCity(customerDetails.getCity());
        customer.setCountry(customerDetails.getCountry());
        customer.setNumId(customerDetails.getNumId());
        customer.setTelephone(customerDetails.getTelephone());
        customer.setVat(customerDetails.getVat());
        customer.setZipcode(customerDetails.getZipcode());
        Customer updatedCustomer = customerRepo.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));

        customerRepo.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}

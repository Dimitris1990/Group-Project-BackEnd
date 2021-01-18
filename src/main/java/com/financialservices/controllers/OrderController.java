package com.financialservices.controllers;

import com.financialservices.exception.ResourceNotFoundException;
import com.financialservices.repository.OrderRepository;
import com.financialservices.models.Orders;
import com.financialservices.models.Useraccount;
import com.financialservices.repository.UseraccountRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UseraccountRepository useraccountRepo;

    @GetMapping("/orders")
    public List<Orders> getOrders() {
        return orderRepo.findAll();
    }

    @GetMapping(value = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + id));
        return ResponseEntity.ok(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id,
            @RequestBody Orders orderDetails) {
        Orders order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + id));

        order.setActv(orderDetails.getActv());
        Orders updateOrder = orderRepo.save(order);
        return ResponseEntity.ok(updateOrder);
    }

    @GetMapping(value = "/accounts/{id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> getOrdersPerUseraccount(@PathVariable(value = "id") Long id) {
        return orderRepo.findByUseraccountId(id);
    }

    @PostMapping("/accounts/{accountId}/orders")
    public ResponseEntity<Orders> createOrder(@PathVariable(value = "accountId") Long accountId,
            @RequestBody Orders order) {
        Useraccount useraccount = useraccountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + accountId));
        order.setUseraccount(useraccount);
        order.setActv(Boolean.TRUE);
        Date dateTimeOfOrder = new Date(System.currentTimeMillis());
        order.setTimestmp(dateTimeOfOrder);
        Orders newOrder = orderRepo.save(order);
        return ResponseEntity.ok(newOrder);
    }

}

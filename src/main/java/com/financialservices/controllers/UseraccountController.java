
package com.financialservices.controllers;

import com.financialservices.exception.ResourceNotFoundException;
import com.financialservices.models.Useraccount;
import com.financialservices.repository.UseraccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UseraccountController {

    @Autowired
    private UseraccountRepository useraccountRepo;

    @GetMapping("/accounts")
    public List<Useraccount> getUseruseraccount() {
        return useraccountRepo.findAll();
    }


    @GetMapping(value = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Useraccount> getUserAccountById(@PathVariable Long id) {
        Useraccount useraccount = useraccountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
        return ResponseEntity.ok(useraccount);
    }

  
    @PutMapping("/accounts/{id}")
    public ResponseEntity<Useraccount> updateUserAccount(@PathVariable Long id, @RequestBody Useraccount accountDetails) {
        Useraccount userAccount = useraccountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));

        userAccount.setBalance(accountDetails.getBalance());
        Useraccount updatedAccount = useraccountRepo.save(userAccount);
        return ResponseEntity.ok(updatedAccount);
    }
    
}

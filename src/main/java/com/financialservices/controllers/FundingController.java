
package com.financialservices.controllers;

import com.financialservices.exception.ResourceNotFoundException;
import com.financialservices.models.Funding;
import com.financialservices.models.Useraccount;
import com.financialservices.repository.FundingRepository;
import com.financialservices.repository.UseraccountRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FundingController {
    
    
    @Autowired
    private FundingRepository fundingRepo;
    
    
    @Autowired
    private UseraccountRepository useraccountRepo;
    
    
    @GetMapping("/fundings")
    public List<Funding> getFundings() {
        return fundingRepo.findAll();
    }
    
    
    @GetMapping(value = "/accounts/{id}/fundings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Funding> getFundingsByUseraccount(@PathVariable(value = "id") Long id) {
        return fundingRepo.findByUseraccountId(id);
    }

    @PostMapping("/accounts/{useraccountId}/deposit")
    public Funding createDeposit(@PathVariable(value = "useraccountId") Long useraccountId,
            @RequestBody Funding funding) {
        Useraccount useraccount = useraccountRepo.findById(useraccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + useraccountId));
        funding.setUseraccount(useraccount);
        funding.setWithdrawal(BigDecimal.ZERO);
        Date dateTime = new Date(System.currentTimeMillis());
        funding.setFdate(dateTime);
        return fundingRepo.save(funding);
    }
    
      @PostMapping("/accounts/{useraccountId}/withdrawal")
      public ResponseEntity<Funding> createWithdrawal(@PathVariable(value = "useraccountId") Long useraccountId,
             @RequestBody Funding funding) {
        Useraccount useraccount = useraccountRepo.findById(useraccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + useraccountId));
        funding.setUseraccount(useraccount);
        funding.setDeposit(BigDecimal.ZERO);
        Date dateTime = new Date(System.currentTimeMillis());
        funding.setFdate(dateTime);
        Funding withdrawal = fundingRepo.save(funding);
        return ResponseEntity.ok(withdrawal);
    }
    
    
}

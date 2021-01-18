package com.financialservices.controllers;

import com.financialservices.models.Portfolio;
import com.financialservices.repository.PortfolioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired
    private PortfolioRepository portfolioRepo;

    @Autowired
    @GetMapping("/portfolio")
    public List<Portfolio> getPortfolio() {
        return portfolioRepo.findAll();
    }

    @GetMapping(value = "/accounts/{id}/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Portfolio> getPortfolioPerUseraccount(@PathVariable(value = "id") Long id) {
        return portfolioRepo.findByUseraccountId(id);
    }

}

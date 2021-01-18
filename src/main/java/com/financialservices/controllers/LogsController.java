
package com.financialservices.controllers;

import com.financialservices.models.User;
import com.financialservices.models.Userlogs;
import com.financialservices.repository.UserRepository;
import com.financialservices.repository.UserlogsRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author feiba
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LogsController {

    @Autowired
    private UserlogsRepository logsRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/logs")
    public List<Userlogs> getLogs() {
        return logsRepo.findAll();
    }

    @PostMapping("/users/{useraccountId}/logs")
    public Userlogs createLog(@PathVariable(value = "useraccountId") Long useraccountId,
           @RequestBody Userlogs log) {
        User user = userRepo.findById(useraccountId).get();
                
        Date dateTimeLog = new Date(System.currentTimeMillis());
        log.setUser(user);
        log.setUlogs(dateTimeLog);
        return logsRepo.save(log);
    }

    @GetMapping("users/{id}/logs")
    public List<Userlogs> getCreditCardsPerAccount(@PathVariable(value = "id") Long id) {
        return logsRepo.findByUserId(id);
    }
}

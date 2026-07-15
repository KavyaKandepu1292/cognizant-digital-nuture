package com.cognizant.loan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(LoanController.class);

    @GetMapping
    public List<Map<String, Object>> getAllLoans() {
        LOGGER.info("Get all loans called");
        List<Map<String, Object>> loans = new ArrayList<>();

        Map<String, Object> loan1 = new HashMap<>();
        loan1.put("id", 1);
        loan1.put("loanNumber", "LOAN001");
        loan1.put("amount", 100000);
        loan1.put("interestRate", 8.5);
        loans.add(loan1);

        Map<String, Object> loan2 = new HashMap<>();
        loan2.put("id", 2);
        loan2.put("loanNumber", "LOAN002");
        loan2.put("amount", 200000);
        loan2.put("interestRate", 7.5);
        loans.add(loan2);

        return loans;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getLoanById(@PathVariable int id) {
        LOGGER.info("Get loan by id={}", id);
        Map<String, Object> loan = new HashMap<>();
        loan.put("id", id);
        loan.put("loanNumber", "LOAN00" + id);
        loan.put("amount", 100000 * id);
        loan.put("interestRate", 8.5);
        return loan;
    }
}
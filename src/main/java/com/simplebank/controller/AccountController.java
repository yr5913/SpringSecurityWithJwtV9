package com.simplebank.controller;


import com.simplebank.entity.Accounts;
import com.simplebank.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/get")
    public Accounts getAccountDetails(@RequestParam int id) {
        return accountsRepository.findByCustomerId(id);
    }
}

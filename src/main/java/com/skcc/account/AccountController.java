package com.skcc.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account/{user}/{password}/{role}")
    public Account createAccount(@ModelAttribute Account account){
        return accountService.createNew(account);
    }
}

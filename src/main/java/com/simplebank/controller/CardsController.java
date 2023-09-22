package com.simplebank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards/")
public class CardsController {

    @GetMapping("getDetails")
    public String getAccountDetails() {
        return "cards";
    }
}

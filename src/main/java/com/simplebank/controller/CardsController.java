package com.simplebank.controller;

import com.simplebank.entity.Cards;
import com.simplebank.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/get")
    public List<Cards> getCardDetails(@RequestParam int id) {
        return cardsRepository.findByCustomerId(id);
    }
}

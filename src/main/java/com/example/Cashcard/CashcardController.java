package com.example.Cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cashcards")
public class CashcardController {

    @GetMapping("{requestedId}")
    public ResponseEntity<CashCard> findById() {

        CashCard cashCard = new CashCard(99L, 100.5);

        return ResponseEntity.ok(cashCard);
    }
}

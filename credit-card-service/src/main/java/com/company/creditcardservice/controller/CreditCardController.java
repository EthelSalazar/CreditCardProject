package com.company.creditcardservice.controller;

import com.company.creditcardservice.model.CreditCard;
import com.company.creditcardservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {

    @Autowired
    CreditCardService service;

    @RequestMapping(value = "/creditcard/debitfunds", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCard updateBalance(@RequestBody CreditCard creditCard){
        return service.updateBalance(creditCard.getNumber(), creditCard.getAmount());
    }

    @RequestMapping(value = "/creditcard/{cardNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public double getBalance(@PathVariable int cardNumber){
        return service.getBalanceByCardNumber(cardNumber);
    }
}

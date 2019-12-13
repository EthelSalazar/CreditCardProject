package com.company.bankingservice.controller;

import com.company.bankingservice.model.CreditCard;
import com.company.bankingservice.util.feign.CreditCardClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankingController {

    @Autowired
    CreditCardClient client;

    @RequestMapping(value = "/creditcard/debitfunds", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCard updateBalance(@RequestBody CreditCard creditCard){
        return client.updateBalance(creditCard);
    }

    @RequestMapping(value = "/creditcard/{cardNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public double getBalance(@PathVariable int cardNumber){
        return client.getBalance(cardNumber);
    }

}

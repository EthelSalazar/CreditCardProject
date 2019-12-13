package com.company.creditcardservice.service;

import com.company.creditcardservice.model.CreditCard;
import com.company.creditcardservice.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CreditCardService {
    CreditCardRepository repo;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository){
        this.repo = creditCardRepository;
    }

    @Transactional
    public CreditCard updateBalance(int cardNumber, double balance){
        CreditCard creditCard = new CreditCard();
        creditCard = repo.findById(cardNumber).get();

        double currentBalance = creditCard.getAmount();

        if(balance<0){
            currentBalance -= balance;
        } else {
            currentBalance += balance;
        }
        creditCard.setAmount(currentBalance);

        return creditCard;
    }

    public double getBalanceByCardNumber(int cardNumber){
        return repo.findById(cardNumber).get().getAmount();
    }

}

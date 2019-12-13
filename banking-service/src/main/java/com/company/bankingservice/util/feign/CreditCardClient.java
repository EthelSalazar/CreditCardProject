package com.company.bankingservice.util.feign;

import com.company.bankingservice.model.CreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "credit-card-service")
public interface CreditCardClient {

    @RequestMapping(value = "/creditcard/debitfunds", method = RequestMethod.PUT)
    public CreditCard updateBalance(@RequestBody CreditCard creditCard);

    @RequestMapping(value = "/creditcard/{cardNumber}", method = RequestMethod.GET)
    public double getBalance(@PathVariable int cardNumber);
}

package com.company.creditcardservice.service;

import com.company.creditcardservice.model.CreditCard;
import com.company.creditcardservice.repository.CreditCardRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CreditCardServiceTest {
    CreditCardRepository repo;
    CreditCardService service;

    @Before
    public void setUp() throws Exception {
        repo = mock(CreditCardRepository.class);

        setUpCreditCardMock();

        service = new CreditCardService(repo);

    }

    @Test
    public void shouldUpdateBalance(){
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(4410);
        creditCard.setAmount(457789.00);

        CreditCard creditCardUpdated = service.updateBalance(creditCard.getNumber(), 1000);

        assertEquals(creditCard, creditCardUpdated);

    }

    @Test
    public void shouldGetBalance(){
        double balanceExpected = 456789.00;

        double balanceFromService = service.getBalanceByCardNumber(4410);

        assertEquals(balanceExpected, balanceFromService,2);

    }

    private void setUpCreditCardMock() {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(4410);
        creditCard.setAmount(456789.00);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setNumber(4410);
        creditCard2.setAmount(457789.00);

        Optional<CreditCard> optionalCreditCard = Optional.of(creditCard);

        doReturn(creditCard2).when(repo).save(creditCard2);
        doReturn(optionalCreditCard).when(repo).findById(4410);
    }

}
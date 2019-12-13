package com.company.creditcardservice.controller;

import com.company.creditcardservice.model.CreditCard;
import com.company.creditcardservice.service.CreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {

    @MockBean
    CreditCardService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        setUpServiceMock();
    }

    @Test
    public void shouldUpdateBalance() throws Exception{
        CreditCard inputValues = new CreditCard();
        inputValues.setNumber(4410);
        inputValues.setAmount(1000);

        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(4410);
        creditCard.setAmount(457789.00);

        String input = mapper.writeValueAsString(inputValues);
        String outPut = mapper.writeValueAsString(creditCard);


        mockMvc.perform(
                put("/creditcard/debitfunds")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().json(outPut));

    }

    @Test
    public void shouldGetBalance() throws Exception{

        double balanceExpected = 456789.00;

        double balanceFromService = service.getBalanceByCardNumber(4410);
        String outPut = mapper.writeValueAsString(balanceFromService);

        mockMvc.perform(
                get("/creditcard/{cardNumber}", 4410)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("456789.0"));


    }


    private void setUpServiceMock() {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(4410);
        creditCard.setAmount(456789.00);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setNumber(4410);
        creditCard2.setAmount(457789.00);

        Optional<CreditCard> optionalCreditCard = Optional.of(creditCard);

        doReturn(creditCard2).when(service).updateBalance(creditCard.getNumber(), 1000);
        doReturn(creditCard.getAmount()).when(service).getBalanceByCardNumber(4410);
    }


}
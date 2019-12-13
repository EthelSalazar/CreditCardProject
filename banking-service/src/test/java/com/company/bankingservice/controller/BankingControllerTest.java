package com.company.bankingservice.controller;

import com.company.bankingservice.model.CreditCard;
import com.company.bankingservice.util.feign.CreditCardClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankingControllerTest {

    @MockBean
    CreditCardClient client;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        setUpClientMock();
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
    public void shouldGetBalance() throws Exception {

        mockMvc.perform(
                get("/creditcard/{cardNumber}", 4410)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("456789.0"));


    }

    private void setUpClientMock() {

        CreditCard inputCreditC = new CreditCard();
        inputCreditC.setNumber(4410);
        inputCreditC.setAmount(1000);


        CreditCard creditCard2 = new CreditCard();
        creditCard2.setNumber(4410);
        creditCard2.setAmount(457789.00);

        doReturn(456789.00).when(client).getBalance(4410);
        //doReturn(creditCard2).when(client).updateBalance(inputCreditC);
    }

}
package com.company.creditcardservice.repository;

import com.company.creditcardservice.model.CreditCard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardRepositoryTest {

 @Autowired
     CreditCardRepository repo;
 
     @Before
     public void setUp() throws Exception {
         repo.deleteAll();
     }
     @Test
     public void shouldAddAndGetCreditCard(){
         CreditCard creditCard = new CreditCard();
         creditCard.setNumber(4410);
         creditCard.setAmount(456789);
         repo.save(creditCard);

         Optional<CreditCard> optionalCreditCard = Optional.of(creditCard);
 
         assertEquals(creditCard, repo.findById(creditCard.getNumber()).get());
     }
     @Test
     public void shouldGetAllCreditCard(){
         CreditCard creditCard = new CreditCard();
         creditCard.setNumber(4410);
         creditCard.setAmount(456789);
         repo.save(creditCard);

         CreditCard creditCard2 = new CreditCard();
         creditCard2.setNumber(4411);
         creditCard2.setAmount(456789);
         repo.save(creditCard2);
 
         List<CreditCard> creditCardList = new ArrayList<>();
         creditCardList.add(creditCard);
         creditCardList.add(creditCard2);
 
         assertEquals(creditCardList, repo.findAll());
     }
 

 
     @Test
     public void shouldUpdateCreditCard(){
         CreditCard creditCard = new CreditCard();
         creditCard.setNumber(4410);
         creditCard.setAmount(456789);
         repo.save(creditCard);
 
         CreditCard creditCard2 = repo.getOne(creditCard.getNumber());
         creditCard2.setAmount(456789);
         repo.save(creditCard2);
 
         assertEquals(creditCard2, repo.getOne(creditCard.getNumber()));
     }
 

}
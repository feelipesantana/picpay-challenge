package com.picpaychallenge.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaychallenge.domain.transaction.Transaction;
import com.picpaychallenge.domain.user.User;
import com.picpaychallenge.dtos.TransactionDTO;
import com.picpaychallenge.repositories.TransactionRepository;

@Service
public class TransactionService {
  @Autowired
  private UserService userService;

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private RestTemplate restTemplate;

  public void createTransaction(TransactionDTO transaction) throws Exception{
    User sender = this.userService.findUserById(transaction.senderId());
    User receiver = this.userService.findUserById(transaction.receiveId());

    userService.validateTransaction(sender, transaction.value());

    boolean isAuthorize = this.authorizeTrancation(sender, transaction.value());

    if(!isAuthorize){
      throw new Exception("Transação não autorizado");
    }

    Transaction newtransaction = new Transaction();

    newtransaction.setAmount(transaction.value());
    newtransaction.setSender(sender);
    newtransaction.setReceiver(receiver);
    newtransaction.setTimestamp(LocalDateTime.now());


    sender.setBalance(sender.getBalance().subtract(transaction.value()));
    receiver.setBalance(receiver.getBalance().add(transaction.value()));
    

    this.transactionRepository.save(newtransaction);
    this.userService.saveUser(sender);
    this.userService.saveUser(receiver);




  }

  public boolean authorizeTrancation(User sender, BigDecimal value){
      ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",Map.class);

      if(authorizationResponse.getStatusCode() == HttpStatus.OK){
        String message = (String) authorizationResponse.getBody().get("message");

        return "Autorizado".equalsIgnoreCase(message);
      }else return false;
  }

   
}

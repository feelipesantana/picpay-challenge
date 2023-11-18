package com.picpaychallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaychallenge.domain.user.User;
import com.picpaychallenge.dtos.NotificationDTO;

@Service

public class NotificationService {
  @Autowired
  private RestTemplate restTemplate;

  public void sendNotification(User user, String message) throws Exception{
    
    String email =  user.getEmail();
    NotificationDTO notificationRequest = new NotificationDTO(email, message);

    // ResponseEntity<String> notificationReponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class );
    
    // if(notificationReponse.getStatusCode() == HttpStatus.OK){
    //   System.out.println("Erro ao enviar notificação");
    //   throw new Exception("Serviço de notificação está fora do ar");
    // }
    System.out.println("Notificação Criada para o usuário");
  }
}

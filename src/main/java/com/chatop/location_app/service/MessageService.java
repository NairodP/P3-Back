package com.chatop.location_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.location_app.model.Message;
import com.chatop.location_app.repository.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  public Message createMessage(Message message) {
    return messageRepository.save(message);
  }
}

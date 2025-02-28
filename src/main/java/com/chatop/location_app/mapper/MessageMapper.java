package com.chatop.location_app.mapper;

import org.springframework.stereotype.Component;

import com.chatop.location_app.dto.MessageDto;
import com.chatop.location_app.model.Message;
import com.chatop.location_app.model.Rental;
import com.chatop.location_app.model.User;

@Component
public class MessageMapper {

  public Message mapToMessage(MessageDto dto, User user, Rental rental) {
    return Message.builder()
            .user(user)
            .rental(rental)
            .message(dto.getMessage())
            .build();
  }

  public MessageDto mapToMessageDTO(Message message) {
    return MessageDto.builder()
            .id(message.getId())
            .user_id(message.getUser().getId())
            .rental_id(message.getRental().getId())
            .message(message.getMessage())
            .created_at(message.getCreatedAt().toString())
            .updated_at(message.getUpdatedAt() != null ? message.getUpdatedAt().toString() : null)
            .build();
  }
}
package com.chatop.location_app.controller;

import com.chatop.location_app.dto.MessageDto;
import com.chatop.location_app.mapper.MessageMapper;
import com.chatop.location_app.model.Message;
import com.chatop.location_app.model.Rental;
import com.chatop.location_app.model.User;
import com.chatop.location_app.service.MessageService;
import com.chatop.location_app.service.RentalService;
import com.chatop.location_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Messages API", description = "Contains all operations that handle messages")

public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private UserService userService;

  @Autowired
  private RentalService rentalService;

  @Autowired
  private MessageMapper messageMapper;

  @Operation(
          summary = "Create a new message",
          description = "Creates a message from the current user to a specific rental",
          responses = {
                  @ApiResponse(
                          responseCode = "201",
                          description = "Message created successfully",
                          content = @Content(schema = @Schema(implementation = MessageDto.class))
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Invalid input"
                  ),
                  @ApiResponse(
                          responseCode = "404",
                          description = "Rental not found"
                  )
          }
  )
  @PostMapping({"/messages", "/messages/"})
  public ResponseEntity<MessageDto> createMessage(@Valid @RequestBody MessageDto messageData) {

    User currentUser = userService.getCurrentUser();
    Rental rental = rentalService.getRentalByID(messageData.getRental_id());

    Message createdMessage = messageMapper.mapToMessage(messageData, currentUser, rental);

    createdMessage = messageService.createMessage(createdMessage);
    messageData = messageMapper.mapToMessageDTO(createdMessage);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(messageData);
  }
}
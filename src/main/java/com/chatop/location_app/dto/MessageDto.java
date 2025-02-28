package com.chatop.location_app.dto;

import com.chatop.location_app.model.Message;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Message Data Transfer Object", name = "Message")
public class MessageDto {
  @Schema(description = "Message unique identifier", example = "1")
  private Integer id;

  @Schema(description = "ID of the user who sent the message")
  private Integer user_id;

  @Schema(description = "ID of the rental this message is about", required = true)
  @NotNull
  private Integer rental_id;

  @Schema(description = "Content of the message", required = true)
  @NotNull()
  @NotEmpty()
  @Size(max = Message.MESSAGE_MAX_SIZE)
  private String message;

  @Schema(description = "Date and time the message was created")
  private String created_at;

  @Schema(description = "Date and time the message was last updated")
  private String updated_at;
}
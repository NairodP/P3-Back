package com.chatop.location_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Exception Data Transfer Object", name = "Exception")
public class ExceptionDto {

  @Schema(description = "Exception message", example = "An error occurred")
  public String message;
  @Schema(description = "Exception details", example = "java.lang.Exception")
  public String details;

  public ExceptionDto(Exception e) {
    message = e.getMessage();
    details = e.getClass().toString();
  }
}
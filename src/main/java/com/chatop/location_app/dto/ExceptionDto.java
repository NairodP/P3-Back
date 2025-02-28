package com.chatop.location_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto {

  public String message;
  public String details;

  public ExceptionDto(Exception e) {
    message = e.getMessage();
    details = e.getClass().toString();
  }
}
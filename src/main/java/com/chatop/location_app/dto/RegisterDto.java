package com.chatop.location_app.dto;


import com.chatop.location_app.model.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterDto extends LoginDto {

  @NotNull(message = "A name must be provided")
  @NotEmpty(message = "Name must not be empty")
  @Size(max = User.NAME_MAX_SIZE)
  private String name;

}
package com.chatop.location_app.dto;


import com.chatop.location_app.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Schema(description = "Register Data Transfer Object", name = "Register")
public class RegisterDto extends LoginDto {

  @Schema(description = "User name", example = "John Doe")
  @NotNull(message = "A name must be provided")
  @NotEmpty(message = "Name must not be empty")
  @Size(max = User.NAME_MAX_SIZE)
  private String name;

}
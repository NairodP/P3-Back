package com.chatop.location_app.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import com.chatop.location_app.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Login Data Transfer Object", name = "Login")
public class LoginDto {

  @Schema(description = "User email", example = "exemple@email.com")
  @Email(message = "Invalid email")
  @NotNull(message = "An email must be provided")
  @NotEmpty(message = "Email must not be empty")
  @Size(max = User.EMAIL_MAX_SIZE)
  protected String email;

  @Schema(description = "User password", example = "password")
  @NotNull(message = "A password must be provided")
  @NotEmpty(message = "Password must not be empty")
  @Size(max = User.PASSWORD_MAX_SIZE)
  protected String password;
}
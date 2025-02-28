package com.chatop.location_app.controller;

import com.chatop.location_app.dto.*;
import com.chatop.location_app.service.JWTService;
import com.chatop.location_app.service.UserService;
import com.chatop.location_app.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Authentication API", description = "Contains all the operations that can be performed a Auth + the endpoints to retreive an user from his id.")
@RestController
public class AuthController {

  @Autowired
  private UserService userservice;

  @Autowired
  private final JWTService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  public AuthController(JWTService jwtService) {
    this.jwtService = jwtService;
  }

  private ResponseEntity<TokenDto> getToken(String email) {
    String jwt = jwtService.generateToken(email);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(new TokenDto(jwt));
  }

  @Operation(summary = "Register a user, by provinding name, email and password.")
  @PostMapping("/auth/register")
  @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDto.class)))
  public ResponseEntity<TokenDto> register(@Valid @RequestBody RegisterDto registerDatas) {

    String email = registerDatas.getEmail();

    if (userservice.userExistsByEmail(email))
      throw new RuntimeException("User already exists with email " + email);

    userservice.createUser(new User(
            registerDatas.getName(),
            registerDatas.getEmail(),
            registerDatas.getPassword()));

    return getToken(email);
  }

  @Operation(
          summary = "Login a user with email and password",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Authentication successful",
                          content = @Content(schema = @Schema(implementation = TokenDto.class))
                  ),
                  @ApiResponse(
                          responseCode = "401",
                          description = "Bad credentials",
                          content = @Content(schema = @Schema(implementation = ExceptionDto.class))
                  )
          }
  )
  @PostMapping("/auth/login")
  @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDto.class)))
  public ResponseEntity<TokenDto> login(@RequestBody LoginDto data) {

    var credentials = new UsernamePasswordAuthenticationToken(
            data.getEmail(),
            data.getPassword());

    if (!userservice.userExistsByEmail(data.getEmail()))
      throw new BadCredentialsException("Bad credentials");

    Authentication authentication = authenticationManager.authenticate(credentials);

    if (!authentication.isAuthenticated())
      throw new BadCredentialsException("Bad credentials");

    return getToken(data.getEmail());
  }

  @GetMapping("/auth/me")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Retreive the authenticated user, from the jwt sent in the header request.")
  @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
  public ResponseEntity<UserDto> me() {

    User user = userservice.getCurrentUser();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt().toString(),
                    user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null
            ));
  }

  @Operation(summary = "Retreive an user from his unique id")
  @GetMapping("/user/{id}")
  @SecurityRequirement(name = "Bearer Authentication")
  public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {

    User currentUser = userservice.findByID(id);

    UserDto userDTO = UserDto.builder()
            .id(currentUser.getId())
            .name(currentUser.getName())
            .email(currentUser.getEmail())
            .created_at(currentUser.getCreatedAt().toString())
            .updated_at(currentUser.getUpdatedAt() != null ? currentUser.getUpdatedAt().toString() : null)
            .build();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userDTO);
  }
}
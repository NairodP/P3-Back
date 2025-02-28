package com.chatop.location_app.controller;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.stream.StreamSupport;

import com.chatop.location_app.dto.RentalRequestDto;
import com.chatop.location_app.dto.RentalResponse;
import com.chatop.location_app.dto.RentalsResponse;
import com.chatop.location_app.mapper.RentalMapper;
import com.chatop.location_app.model.Rental;
import com.chatop.location_app.model.User;
import com.chatop.location_app.service.RentalService;
import com.chatop.location_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Rentals API", description = "Contains all operations to create, retreive and update a rental.")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @Autowired
  private UserService userService;

  @Autowired
  private RentalMapper rentalMapper;

  @Operation(summary = "Create a new rental")
  @PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<RentalResponse> createRental(@ModelAttribute RentalRequestDto dto) throws IOException {
    User currentUser = userService.getCurrentUser();
    Rental createdRental = rentalService.createRental(dto, currentUser);
    RentalResponse result = rentalMapper.mapToRentalResponse(createdRental);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(summary = "Retreive all rentals")
  @GetMapping("/rentals")
  public ResponseEntity<RentalsResponse> getAllRentals() {

    RentalResponse[] rentals = StreamSupport.stream(rentalService.getRentals().spliterator(), false).map(rentalMapper::mapToRentalResponse).toArray(RentalResponse[]::new);

    RentalsResponse response = RentalsResponse.builder().rentals(rentals).build();

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Retreive a rental from his unique id")
  @GetMapping("/rentals/{id}")
  public ResponseEntity<RentalResponse> rentalByID(@PathVariable Integer id) {

    Rental rental = rentalService.getRentalByID(id);
    RentalResponse dto = rentalMapper.mapToRentalResponse(rental);

    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }

  @Operation(summary = "Update an existing rental from his unique id, and from the updated datas.")
  @PutMapping(value = "/rentals/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<RentalResponse> updateRental(
          @PathVariable Integer id,
          @ModelAttribute RentalRequestDto dto) throws IOException {

    Rental rental = rentalService.getRentalByID(id);
    User currentUser = userService.getCurrentUser();

    if (!rental.getOwner().getId().equals(currentUser.getId()))
      throw new AccessDeniedException("You are not allowed to update rental " + id);

    Rental updatedRental = rentalService.updateRental(id, dto);
    RentalResponse result = rentalMapper.mapToRentalResponse(updatedRental);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
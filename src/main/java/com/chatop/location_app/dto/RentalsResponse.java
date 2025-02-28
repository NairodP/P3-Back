package com.chatop.location_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Rentals Response Data Transfer Object", name = "RentalsResponse")
@Data
@Builder
public class RentalsResponse {
  @Schema(description = "Rentals list")
  private RentalResponse[] rentals;
}

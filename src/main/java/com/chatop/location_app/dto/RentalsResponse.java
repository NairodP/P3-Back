package com.chatop.location_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalsResponse {
  private RentalResponse[] rentals;
}

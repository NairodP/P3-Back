package com.chatop.location_app.mapper;

import org.springframework.stereotype.Component;

import com.chatop.location_app.dto.RentalRequestDto;
import com.chatop.location_app.dto.RentalResponse;
import com.chatop.location_app.model.Rental;
import com.chatop.location_app.model.User;

@Component
public class RentalMapper {

  public Rental mapToRental(RentalRequestDto dto, User owner, String pictureUrl) {

    return Rental.builder()
            .name(dto.getName())
            .surface(dto.getSurface())
            .price(dto.getPrice())
            .picture(pictureUrl)
            .description(dto.getDescription())
            .owner(owner)
            .build();
  }

  public RentalResponse mapToRentalResponse(Rental rental) {

    return RentalResponse.builder()
            .id(rental.getId())
            .name(rental.getName())
            .surface(rental.getSurface())
            .price(rental.getPrice())
            .picture(rental.getPicture())
            .description(rental.getDescription())
            .owner_id(rental.getOwner().getId())
            .created_at(rental.getCreatedAt().toString())
            .updated_at(rental.getUpdatedAt() != null ? rental.getUpdatedAt().toString() : null)
            .build();
  }

}
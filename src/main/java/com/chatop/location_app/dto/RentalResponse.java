package com.chatop.location_app.dto;

import com.chatop.location_app.model.Rental;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalResponse {

  public Integer id;

  @NotNull()
  @NotEmpty()
  @Size(max = Rental.NAME_MAX_SIZE)
  public String name;

  @NotNull()
  public Integer surface;

  @NotNull()
  public Float price;

  @Size(max = Rental.IMG_URL_MAX_SIZE)
  public String picture;

  @NotNull()
  @NotEmpty()
  @Size(max = Rental.DESC_MAX_SIZE)
  public String description;

  public String created_at;

  public String updated_at;

  public Integer owner_id;
}
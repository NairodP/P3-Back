package com.chatop.location_app.dto;

import com.chatop.location_app.model.Rental;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Rental Data Transfer Object", name = "Rental")
public class RentalResponse {

  @Schema(description = "Rental unique identifier", example = "1")
  public Integer id;

  @Schema(description = "Rental name", example = "Nice house")
  @NotNull()
  @NotEmpty()
  @Size(max = Rental.NAME_MAX_SIZE)
  public String name;

  @Schema(description = "Rental surface in square meters", example = "100")
  @NotNull()
  public Integer surface;

  @Schema(description = "Rental price per month", example = "1000.0")
  @NotNull()
  public Float price;

  @Schema(description = "Rental picture", example = "https://www.example.com/picture.jpg")
  @Size(max = Rental.IMG_URL_MAX_SIZE)
  public String picture;

  @Schema(description = "Rental description", example = "This is a nice house")
  @NotNull()
  @NotEmpty()
  @Size(max = Rental.DESC_MAX_SIZE)
  public String description;

  @Schema(description = "Date and time the rental was created")
  public String created_at;

  @Schema(description = "Date and time the rental was last updated and if it was not updated, it is null")
  public String updated_at;

  @Schema(description = "ID of the owner of the rental")
  public Integer owner_id;
}
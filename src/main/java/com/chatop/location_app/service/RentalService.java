package com.chatop.location_app.service;

import java.io.IOException;
import java.util.Map;

import com.chatop.location_app.dto.RentalRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.location_app.Exceptions.ResourceNotFoundException;
import com.chatop.location_app.dto.RentalDto;
import com.chatop.location_app.mapper.RentalMapper;
import com.chatop.location_app.model.Rental;
import com.chatop.location_app.model.User;
import com.chatop.location_app.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private CloudinaryService cloudinaryService;

  @Autowired
  private RentalMapper rentalMapper;

  public Iterable<Rental> getRentals() {
    return rentalRepository.findAll();
  }

  public Rental createRental(RentalRequestDto dto, User owner) throws IOException {
    String pictureUrl = null;

    if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
      Map uploadResult = cloudinaryService.uploadImage(dto.getPicture());
      pictureUrl = (String) uploadResult.get("url");
    }

    Rental rental = rentalMapper.mapToRental(dto, owner, pictureUrl);
    return rentalRepository.save(rental);
  }

  public Rental getRentalByID(Integer id) {
    return rentalRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + id));
  }

  public Rental updateRental(Integer id, RentalRequestDto dto) throws IOException {
    Rental rental = getRentalByID(id);

    if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
      Map uploadResult = cloudinaryService.uploadImage(dto.getPicture());
      rental.setPicture((String) uploadResult.get("url"));
    }

    if (dto.getName() != null) rental.setName(dto.getName());
    if (dto.getSurface() != null) rental.setSurface(dto.getSurface());
    if (dto.getPrice() != null) rental.setPrice(dto.getPrice());
    if (dto.getDescription() != null) rental.setDescription(dto.getDescription());

    return rentalRepository.save(rental);
  }
}

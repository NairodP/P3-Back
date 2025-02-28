package com.chatop.location_app.service;

import java.io.IOException;

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
  private ImgStorageService imgStorageService;

  @Autowired
  private RentalMapper rentalMapper;

  public Iterable<Rental> getRentals() {
    return rentalRepository.findAll();
  }

  public Rental createRental(RentalDto dto, User owner) throws IOException {

    String pictureUrl = null;

    if (dto.picture != null && !dto.picture.isEmpty()) {
      pictureUrl = imgStorageService.storeImg(dto.picture);
    }

    Rental rental = rentalMapper.mapToRental(dto, owner, pictureUrl);

    return rentalRepository.save(rental);
  }

  public Rental getRentalByID(Integer id) {
    return rentalRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + id));
  }

  public Rental updateRental(Integer id, RentalDto dto) throws IOException {

    Rental rental = getRentalByID(id);

    if (dto.getPicture() != null && !dto.picture.isEmpty()) {
      String pictureUrl = imgStorageService.storeImg(dto.picture);
      rental.setPicture(pictureUrl);
    }

    rental.setName(dto.getName());
    rental.setSurface(dto.getSurface());
    rental.setPrice(dto.getPrice());
    rental.setDescription(dto.getDescription());

    return rentalRepository.save(rental);
  }

}

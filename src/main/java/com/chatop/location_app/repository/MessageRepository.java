package com.chatop.location_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatop.location_app.model.Message;
import com.chatop.location_app.model.Rental;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    Iterable<Message> findByRental(Rental rental);
}
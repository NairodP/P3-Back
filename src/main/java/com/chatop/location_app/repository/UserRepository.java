package com.chatop.location_app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.chatop.location_app.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
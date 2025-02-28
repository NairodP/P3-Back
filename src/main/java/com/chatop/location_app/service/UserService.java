package com.chatop.location_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.location_app.repository.UserRepository;
import com.chatop.location_app.Exceptions.ResourceNotFoundException;
import com.chatop.location_app.model.User;

import lombok.Data;

@Data
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with email: " + email));
    }

    public User findByID(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with id: " + id));
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public Boolean userExistsByEmail(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElse(null);

        return user != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

}
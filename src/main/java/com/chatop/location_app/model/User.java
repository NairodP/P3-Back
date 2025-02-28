package com.chatop.location_app.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
public class User implements UserDetails {

    public static final int NAME_MAX_SIZE = 255;
    public static final int EMAIL_MAX_SIZE = 255;
    public static final int PASSWORD_MAX_SIZE = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = NAME_MAX_SIZE)
    private String name;

    @Column(length = EMAIL_MAX_SIZE, unique = true)
    private String email;

    @Column(length = PASSWORD_MAX_SIZE)
    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "owner")
    private Set<Rental> rentals;

    @OneToMany(mappedBy = "user")
    private Set<Message> messages;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // Constructor
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

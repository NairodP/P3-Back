package com.chatop.location_app.model;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RENTALS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    public static final int NAME_MAX_SIZE = 255;
    public static final int IMG_URL_MAX_SIZE = 255;
    public static final int DESC_MAX_SIZE = 2000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = NAME_MAX_SIZE)
    private String name;

    @Column
    private Integer surface;

    @Column
    private Float price;

    @Column(length = IMG_URL_MAX_SIZE)
    private String picture;

    @Column(length = DESC_MAX_SIZE)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "rental")
    private Set<Message> messages;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
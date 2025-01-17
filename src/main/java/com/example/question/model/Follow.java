package com.example.question.model;
import jakarta.persistence.*;

import java.io.Serializable;
import lombok.*;
@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "following_id", nullable = false)
    private Long followingId;

    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @ManyToOne
    @JoinColumn(name = "following_id", insertable = false, updatable = false)
    private User following;

    @ManyToOne
    @JoinColumn(name = "follower_id", insertable = false, updatable = false)
    private User follower;

    // Getters and Setters
}

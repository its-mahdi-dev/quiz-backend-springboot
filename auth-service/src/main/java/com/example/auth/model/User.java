package com.example.auth.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(nullable = false, length = 11, unique = true)
    private String phone;

    @Column(nullable = true, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 500)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    @Column(nullable = false)
    private Integer score = 0;

    public void setType(String newType) {
        type = UserType.valueOf(newType.toUpperCase());
    }

    public static UserType getuUserType(String type) {
        return UserType.valueOf(type.toUpperCase());
    }

}

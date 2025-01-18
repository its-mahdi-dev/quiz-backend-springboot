package com.example.question.model;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "user")
    private List<Question> questions;

    @OneToMany(mappedBy = "following")
    private List<Follow> following;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followers;

    @OneToMany(mappedBy = "user")
    private List<UserAnswer> userAnswers;

    public void setType(String newType){
        type = UserType.valueOf(newType.toUpperCase());
    }

    public enum UserType {
        designer, player
    }

    public User maskData(String[] attributes) {
        if (attributes == null || attributes.length == 0) {
            return null; // Return null if input is invalid
        }

        try {
            // Create a new User object to populate masked fields
            User maskedUser = new User();
            List<String> attrList = Arrays.asList(attributes);
            Class<?> userClass = this.getClass();

            // Iterate over all fields of the User class
            for (Field field : userClass.getDeclaredFields()) {
                field.setAccessible(true); // Allow access to private fields
                if (attrList.contains(field.getName())) {
                    // If the field is in the attributes list, copy its value
                    Object value = field.get(this);
                    field.set(maskedUser, value);
                } else {
                    // Otherwise, set the field to null (optional, as new fields are null by default)
                    field.set(maskedUser, null);
                }
            }

            return maskedUser;

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error masking data for User object", e);
        }
    }
}

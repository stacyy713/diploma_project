package com.example.helpsite.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, details;

    private int rating;

    private LocalDate date_creation;

    @ManyToOne
    @JoinColumn(name = "id_user_in_role")
    private UserInRole userInRoleR;

    public Review() {

    }

    public Review(String name, String details, int rating, LocalDate date_creation, UserInRole userInRoleR) {
        this.name = name;
        this.details = details;
        this.rating = rating;
        this.date_creation = date_creation;
        this.userInRoleR = userInRoleR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public UserInRole getUserInRole() {
        return userInRoleR;
    }

    public void setUserInRole(UserInRole userInRoleR) {
        this.userInRoleR = userInRoleR;
    }
}

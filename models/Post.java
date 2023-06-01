package com.example.helpsite.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, full_text;

    private LocalDate date_creation;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_user_in_role")
    private UserInRole userInRole;

    public Post() {
    }

    public Post(String title, String full_text, LocalDate date_creation, Category category, UserInRole userInRole) {
        this.title = title;
        this.full_text = full_text;
        this.date_creation = date_creation;
        this.category = category;
        this.userInRole = userInRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UserInRole getUserInRole() {
        return userInRole;
    }

    public void setUserInRole(UserInRole userInRole) {
        this.userInRole = userInRole;
    }
}

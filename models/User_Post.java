package com.example.helpsite.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_post")
public class User_Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate order_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public User_Post() {
    }

    public User_Post(LocalDate order_date, UserEntity user, Post post) {
        this.order_date = order_date;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

//    public UserInRole getUserInRole() {
//        return userInRole;
//    }
//
//    public void setUserInRole(UserInRole userInRole) {
//        this.userInRole = userInRole;
//    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

package com.example.helpsite.repo;

import com.example.helpsite.models.User_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<User_Post, Long> {
}

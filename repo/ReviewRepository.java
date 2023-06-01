package com.example.helpsite.repo;

import com.example.helpsite.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}

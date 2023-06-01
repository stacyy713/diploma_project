package com.example.helpsite.controllers;

import com.example.helpsite.models.Review;
import com.example.helpsite.models.UserEntity;
import com.example.helpsite.models.UserInRole;
import com.example.helpsite.repo.ReviewRepository;
import com.example.helpsite.repo.UserInRoleRepository;
import com.example.helpsite.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ReviewsController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reviews")
    public String reviewsMain(Model model) {
        Iterable<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "reviews_page";
    }

    @GetMapping("/reviews/add")
    public String reviewAdd(Model model) {
        return "review_add";
    }

    @PostMapping("/reviews/add")
    public String postReviewAdd(@RequestParam String name, @RequestParam String details,  @RequestParam int rating,
                                Model model) {
//        LocalDate date_creation = LocalDate.now();
//        Review review = new Review(name, details, rating, date_creation);

        LocalDate date_creation = LocalDate.now();
        Review review = new Review();
        review.setName(name);
        review.setDetails(details);
        review.setRating(rating);
        review.setDate_creation(date_creation);

        // Отримати авторизованого користувача (authorizedUser)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authorizedUser = authentication.getName();

        UserEntity user = userRepository.findByUsername(authorizedUser);
        Optional<UserInRole> userInRole = userInRoleRepository.findByUserId(user.getId());

        review.setUserInRole(userInRole.get());
        System.out.println("Постраждалий: " + authorizedUser);

        reviewRepository.save(review);
        return "redirect:/reviews";
    }
}

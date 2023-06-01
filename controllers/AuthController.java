package com.example.helpsite.controllers;

import com.example.helpsite.models.Post;
import com.example.helpsite.models.Role;
import com.example.helpsite.models.UserEntity;
import com.example.helpsite.models.UserInRole;
import com.example.helpsite.repo.RoleRepository;
import com.example.helpsite.repo.UserInRoleRepository;
import com.example.helpsite.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserEntity user) {
        // Перевірка, чи користувач з таким ім'ям користувача вже існує
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "error";
        }

        // Хешування пароля перед збереженням в базу даних
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Збереження користувача в базу даних
        userRepository.save(user);

        // Присвоєння ролі "USER" новому користувачу
        Role role = roleRepository.findByName("ROLE_USER");
        UserInRole userRole = new UserInRole();
        List<Post> posts = new ArrayList<>();

        LocalDate date_registration = LocalDate.now();
        userRole.setDate_registration(date_registration);

        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setPosts(posts);

        userInRoleRepository.save(userRole);

        return "redirect:/login";
    }

    @GetMapping("/register_admin")
    public String showRegistrationFormAdmin(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register_admin";
    }

    @PostMapping("/register_admin")
    public String registerAdmin(@ModelAttribute("user") UserEntity user2) {
        // Перевірка, чи користувач з таким ім'ям користувача вже існує
        if (userRepository.findByUsername(user2.getUsername()) != null) {
            return "error";
        }

        // Хешування пароля перед збереженням в базу даних
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));

        // Збереження користувача в базу даних
        userRepository.save(user2);

        // Присвоєння ролі "ADMIN" новому користувачу
        Role role2 = roleRepository.findByName("ROLE_ADMIN");
        UserInRole userRole2 = new UserInRole();
//        List<Post> posts = new ArrayList<>();

        LocalDate date_registration = LocalDate.now();
        userRole2.setDate_registration(date_registration);

        userRole2.setUser(user2);
        userRole2.setRole(role2);
//        userRole2.setPosts(posts);

        userInRoleRepository.save(userRole2);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}

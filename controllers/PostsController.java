package com.example.helpsite.controllers;

import com.example.helpsite.models.*;
import com.example.helpsite.repo.PostRepository;
import com.example.helpsite.repo.UserInRoleRepository;
import com.example.helpsite.repo.UserPostRepository;
import com.example.helpsite.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPostRepository userPostRepository;

    @GetMapping("/posts")
    public String postsMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts_page";
    }

    @GetMapping("/panel_volunteer")
    public String postsVolPanel(Model model) {
        Iterable<Post> posts_vol = postRepository.findAll();
        model.addAttribute("posts_vol", posts_vol);
        return "panel_volunteer";
    }

    @PostMapping("/posts/order")
    public String postOrder(@RequestParam("id") Long id, Model model) {
        LocalDate order_date = LocalDate.now();

        User_Post user_post = new User_Post();
        user_post.setOrder_date(order_date);
        System.out.println(id);

        Optional<Post> post = postRepository.findById(id);
        user_post.setPost(post.get());

        // Отримати авторизованого користувача (authorizedUser)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authorizedUser = authentication.getName();
        UserEntity user = userRepository.findByUsername(authorizedUser);
        user_post.setUser(user);

        System.out.println("Постраждалий: " + authorizedUser + " замовляє допомогу");

        userPostRepository.save(user_post);
        return "success";
    }

    @GetMapping("/posts/add")
    public String postAdd(Model model) {
        return "post_add";
    }

    @PostMapping("/posts/add")
    public String postPostAdd(@RequestParam String title, @RequestParam String full_text,
                              @RequestParam Category category, Model model) {
        LocalDate date_creation = LocalDate.now();
        Post post = new Post();
        post.setTitle(title);
        post.setFull_text(full_text);
        post.setCategory(category);
        post.setDate_creation(date_creation);


        // Отримати авторизованого користувача (authorizedUser)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authorizedUser = authentication.getName();

        UserEntity user = userRepository.findByUsername(authorizedUser);
        Optional <UserInRole> userInRole = userInRoleRepository.findByUserId(user.getId());
        post.setUserInRole(userInRole.get());

        System.out.println("Волонтер: " + authorizedUser);

        postRepository.save(post);
        return "redirect:/panel_volunteer";
    }


    @GetMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)) {
            return "redirect:/posts";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "post_edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String postPostEdit(@PathVariable(value = "id") long id, @RequestParam String title,
                               @RequestParam String full_text, @RequestParam Category category, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setFull_text(full_text);
        post.setCategory(category);

//        Зміна дати створення на дату редагування
        LocalDate date_creation = LocalDate.now();
        post.setDate_creation(date_creation);

        postRepository.save(post);

        return "redirect:/panel_volunteer";
    }

    @PostMapping("/posts/{id}/remove")
    public String postPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);

        return "redirect:/panel_volunteer";
    }
}

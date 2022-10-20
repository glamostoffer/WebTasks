package com.example.webtasks.controllers;

import com.example.webtasks.entities.User;
import com.example.webtasks.repositories.UserRepos;
import com.example.webtasks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String email,
                                           @RequestParam String password) {
        userService.addNewUser(username, email, password);
        return "Saved";
    }

    @PostMapping(path="/add/new")
    public @ResponseBody String addNewUser() {
        userService.addNewUser("username", "email", "password");
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String addNewUser(@RequestParam Integer id) {
        userService.deleteById(id);
        return "Deleted";
    }
}

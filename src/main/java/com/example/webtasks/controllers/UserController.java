package com.example.webtasks.controllers;

import com.example.webtasks.entities.User;
import com.example.webtasks.repositories.UserRepos;
import com.example.webtasks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path="/add") // GET-запрос для возврата странички с возможностью добавлять юзеров
    //@PreAuthorize("hasRole('admin')") // надо сделать чтоб только админ мог добавлять
    public String addNewUser() {
        return "add";
    }
    @PostMapping(path="/add") // POST-запрос для непосредственного создания юзера
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String email,
                                           @RequestParam String password) {
        userService.addNewUser(username, email, password);
        return "Saved";
    }

    @PostMapping(path="/add/admin") // POST-запрос для создания админа (надо сделать по-другому)
    public @ResponseBody String addNewAdmin(@RequestParam String username, @RequestParam String email,
                                           @RequestParam String password) {
        userService.addNewAdmin(username, email, password);
        return "Saved";
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String addNewUser(@RequestParam Long id) {
        userService.deleteById(id);
        return "Deleted";
    }

    @DeleteMapping(path="/delete/all")
    public @ResponseBody String deleteAllUsers() {
        userService.deleteAll();
        return "Deleted";
    }
}

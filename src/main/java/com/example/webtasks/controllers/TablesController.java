package com.example.webtasks.controllers;

import com.example.webtasks.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TablesController {

//    private final TableService tableService;

    @GetMapping(path="/")
    public String getHomePage() {
        return "home";
    }
//    @PostMapping(path="/add/table")
//    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String email,
//                                           @RequestParam String password) {
//        userService.addNewUser(username, email, password);
//        return "Saved";
//    }
}

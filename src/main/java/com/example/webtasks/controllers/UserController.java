package com.example.webtasks.controllers;

import com.example.webtasks.entities.User;
import com.example.webtasks.repositories.UserRepos;
import com.example.webtasks.services.TableService;
import com.example.webtasks.services.UserService;
import com.example.webtasks.viewModels.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

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
    public @ResponseBody RedirectView addNewUser(@RequestParam String username, @RequestParam String email,
                                           @RequestParam String password) {
        userService.addNewUser(username, email, password);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/main");
        return redirectView;
    }

    @PostMapping(path="/add/admin") // POST-запрос для создания админа (надо сделать по-другому)
    public @ResponseBody RedirectView addNewAdmin(@RequestParam String username, @RequestParam String email,
                                           @RequestParam String password) {
        userService.addNewAdmin(username, email, password);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/main");
        return redirectView;
    }

    @GetMapping("/profile")
    public String Profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String Profile(Long id, UserViewModel user, @RequestParam("description") String description, @RequestParam("img") MultipartFile img) {
        userService.changeData(id, user, description);
        return "redirect:/profile";
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

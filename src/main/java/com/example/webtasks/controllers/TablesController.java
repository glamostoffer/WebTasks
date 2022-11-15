package com.example.webtasks.controllers;

import com.example.webtasks.entities.User;
import com.example.webtasks.services.TableService;
import com.example.webtasks.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TablesController {

    private final TableService tableService;

    public TablesController(TableService tableService) {
        this.tableService = tableService;
    }

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

    //Return page to create new table
    @GetMapping("/main")
    public String MainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("AllUserTables", tableService.getUserTables(user));
        return "main";
    }
    //Creating new table
    @PostMapping("/main")
    public String MainPage(@AuthenticationPrincipal User user, String tableName) {
        tableService.createTable(user, tableName);
        return "redirect:/main";
    }

    //Return table page with current id and fill tasks
    @GetMapping("/get/table{id}")
    public String TablePage(@RequestParam Long id, Model model) {

        //Add list of task to page
        model.addAttribute("AllTasks", tableService.getTableTasks(id));
        //Add page object to page
        model.addAttribute("TableObject", tableService.findTableById(id));
        return "table_page";
    }
    @PostMapping("/get/table{id}")
    public String TablePage(@RequestParam Long id, @AuthenticationPrincipal User user,  String task) {
        tableService.AddNewTask(id, user, task);
        return "redirect:/get/table?id=" + id;
    }
}

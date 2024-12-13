package com.shani.journalApp.Controller;


import com.shani.journalApp.Entity.Users;
import com.shani.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){return "Ok";}

    @PostMapping
    public void createUser(@RequestBody Users user) {

        userService.saveNewUser(user);
    }

}

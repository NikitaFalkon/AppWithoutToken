package com.Controllers;

import com.Model.User;
import com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RController {
    @Autowired
    UserService userService;
    @GetMapping("/user")
    public String User(@RequestParam(value = "username") String name, @RequestParam(value = "password") String password)
    {
        User user = userService.login(name, password);
        return user.toString();
    }
}

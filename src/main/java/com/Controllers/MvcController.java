package com.Controllers;

import com.Model.User;
import com.Security.JWT.JwtTokenProvider;
import com.Service.UserService;
import com.dto.AuthenticationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/")
    public String Main()
    {
        return "Main";
    }
    @GetMapping("/find")
    public String FindUser(Model model)
    {
        model.addAttribute("user", new User());
        return "Find";
    }
    @GetMapping("/login")
    public String LoginUser(Model model)
    {
        model.addAttribute("user", new AuthenticationRequestDto());
        return "Login";
    }
    /*@PostMapping("/login")
    public String Log(Model model)
    {
        return "redirect:/users";
    }*/
    @GetMapping(value ="/users")
    public String Users(Model model){
        model.addAttribute("users", userService.readAll());
        return "Users";
    }
}

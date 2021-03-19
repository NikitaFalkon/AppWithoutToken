package com.Controllers;

import com.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
    @GetMapping("/find")
    public String FindUser(Model model)
    {
        model.addAttribute("user", new User());
        return "Find";
    }
}

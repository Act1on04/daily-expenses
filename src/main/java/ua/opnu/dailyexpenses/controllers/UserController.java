package ua.opnu.dailyexpenses.controllers;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.models.User;
import ua.opnu.dailyexpenses.services.UserService;

@Controller
public class UserController {

    private UserService userService;

    @GetMapping("/signup")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "/user/signup";

    }

    @PostMapping("/signup")
    public String saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) return "/user/signup";
        userService.addUser(user);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginUsr(ModelMap model) {
        model.addAttribute("user", new User());
        return "/login";

    }


}

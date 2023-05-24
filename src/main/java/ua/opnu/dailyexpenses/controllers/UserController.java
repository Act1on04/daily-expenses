package ua.opnu.dailyexpenses.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.models.User;
import ua.opnu.dailyexpenses.repositories.UserRepository;
import ua.opnu.dailyexpenses.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    public User loggedUser;
    public Boolean isLogged;

    @GetMapping("/signup")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "/user/signup";

    }

    @PostMapping("/signup")
    public String saveUser(@Valid User user, BindingResult result) {
        if (userRepo.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "error.email","Обліковий запис з такою електронною поштою вже існує");
        }
        if (result.hasErrors()) return "/user/signup";
        userService.addUser(user);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginUserView(ModelMap model) {
        model.addAttribute("user", new User());
        return "/user/login";

    }

    @PostMapping("/login")
    public String loginUser(@Valid User user, BindingResult result) {

        if (result.hasErrors()) return "/user/login";
        loggedUser = user;
        isLogged = true;
        return "redirect:/expanses";
    }
}

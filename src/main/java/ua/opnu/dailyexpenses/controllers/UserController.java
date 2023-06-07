package ua.opnu.dailyexpenses.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.opnu.dailyexpenses.models.User;
import ua.opnu.dailyexpenses.repositories.UserRepository;
import ua.opnu.dailyexpenses.services.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    public User loggedUser = null;
    public Boolean isLogged = false;

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
    public String loginUser(User user, BindingResult result, ModelMap model) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (!userRepo.existsByEmail(email)) {
            result.rejectValue("email", "error.email","Обліковий запис з такою електронною поштою не існує");
            } else {
                if (!userRepo.findByEmail(email).getPassword().equals(password)) {
                    result.rejectValue("password", "error.password", "Не відповідає пароль для облікового запису з такою електронною поштою");
                }
        }
        if (result.hasErrors()) return "/user/login";
        loggedUser = userRepo.findByEmail(email);
        isLogged = true;
        model.addAttribute("isLogged", isLogged);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String loginUser(ModelMap model) {
        if (isLogged) {
            loggedUser = null;
            isLogged = false;
            model.addAttribute("isLogged", isLogged);
        }
        return "redirect:/";
    }

}

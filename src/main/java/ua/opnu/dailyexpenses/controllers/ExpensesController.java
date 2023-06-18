package ua.opnu.dailyexpenses.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.services.ExpenseService;

import java.time.LocalDate;


@Controller
public class ExpensesController {

    @Autowired
    private ExpenseService service;

    @Autowired
    private UserController userController;

    @GetMapping("/")
    public String home(ModelMap model) {
        model.addAttribute("isLogged", userController.isLogged);
        if (userController.isLogged) {
            model.addAttribute("loggedUser", userController.loggedUser);
        }
        return "/index";
    }

    @GetMapping("/expenses")
    public String expensesList(ModelMap model){
        if (userController.isLogged) {
            model.put("expenses", service.getExpensesList());
            model.addAttribute("isLogged", userController.isLogged);
            model.addAttribute("loggedUser", userController.loggedUser);
            model.addAttribute("SumOfExpenses", service.getSumOfAmountFromDate(userController.loggedUser.getId(), LocalDate.now().withDayOfMonth(1)));
            return "/expenses/list";
        } else {
            return "/index";
        }
    }

    @GetMapping("/expenses/add")
    public String newExpense(ModelMap model) {
        if (userController.isLogged) {
            model.addAttribute("expense", new Expense());
            model.addAttribute("title", "Додати нову витрату");
            model.addAttribute("isLogged", userController.isLogged);
            model.addAttribute("loggedUser", userController.loggedUser);
            return "/expenses/edit";
        } else {
            return "/index";
        }
    }

    @PostMapping("/expenses/add")
    public String saveExpense(@Valid Expense expense, BindingResult result) {
        if (result.hasErrors()) return "/expenses/edit";
        service.addExpense(expense, userController.loggedUser.getId());
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/{id}")
    public String updateExpenseView(ModelMap model, @PathVariable Long id) {
        if (userController.isLogged) {
            model.addAttribute("expense", service.findById(id));
            model.addAttribute("title", "Відкорегувати витрату");
            model.addAttribute("isLogged", userController.isLogged);
            model.addAttribute("loggedUser", userController.loggedUser);
            return "/expenses/edit";
        } else {
            return "/index";
        }
    }

    @PostMapping("/expenses/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute("expense") @Valid Expense expense, BindingResult result) {
        if (result.hasErrors()) return "/expenses/edit";
        service.update(id, expense);
        return "redirect:/expenses";
    }

    @DeleteMapping("/expenses/{id}")
    public String deleteExpense(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/expenses";
    }


}

package ua.opnu.dailyexpenses.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.services.ExpenseService;


@Controller
public class ExpensesController {

    @Autowired
    private ExpenseService service;

    @Autowired
    private UserController UsrCntr;

    @GetMapping("/")
    public String home(ModelMap model) {
        model.addAttribute("isLogged", UsrCntr.isLogged);
        return "/index";
    }

    @GetMapping("/expenses")
    public String expensesList(ModelMap model){
        if (UsrCntr.isLogged) {
            model.put("expenses", service.getExpensesList());
            model.addAttribute("isLogged", UsrCntr.isLogged);
            return "/expenses/list";
        } else {
            return "/index";
        }
    }

    @GetMapping("/expenses/add")
    public String newExpense(ModelMap model) {
        if (UsrCntr.isLogged) {
            model.addAttribute("expense", new Expense());
            model.addAttribute("title", "Додати нову витрату");
            model.addAttribute("isLogged", UsrCntr.isLogged);
            return "/expenses/edit";
        } else {
            return "/index";
        }
    }

    @PostMapping("/expenses/add")
    public String saveExpense(@Valid Expense expense, BindingResult result) {
        if (result.hasErrors()) return "/expenses/edit";
        service.addExpense(expense, UsrCntr.loggedUser.getId());
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/{id}")
    public String updateExpenseView(ModelMap model, @PathVariable Long id) {
        if (UsrCntr.isLogged) {
            model.addAttribute("expense", service.findById(id));
            model.addAttribute("title", "Відкорегувати витрату");
            model.addAttribute("isLogged", UsrCntr.isLogged);
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

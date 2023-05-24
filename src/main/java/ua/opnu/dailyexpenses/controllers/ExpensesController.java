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

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/expenses")
    public String expensesList(ModelMap model){
        model.put("expenses", service.getExpensesList());
        return "/expenses/list";
    }

    @GetMapping("/expenses/add")
    public String newExpense(ModelMap model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("title", "Додати нову витрату");
        return "/expenses/edit";
//        return "add_expense";
    }

    @PostMapping("/expenses/add")
    public String saveExpense(@Valid Expense expense, BindingResult result) {
        if (result.hasErrors()) return "/expenses/edit";
        service.addExpense(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/{id}")
    public String updateExpenseView(ModelMap model, @PathVariable Long id) {
        model.addAttribute("expense", service.findById(id));
        model.addAttribute("title", "Відкорегувати витрату");
        return "/expenses/edit";
//        return "update_expense";
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

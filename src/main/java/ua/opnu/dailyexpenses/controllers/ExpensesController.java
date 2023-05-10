package ua.opnu.dailyexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.repositories.ExpenseRepository;
import ua.opnu.dailyexpenses.services.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


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
        return "list_expenses";
    }

    @GetMapping("/expenses/add")
    public String newExpense(ModelMap model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("title", "Додати нову витрату");
        return "/expenses/edit";
//        return "add_expense";
    }

    @PostMapping("/expenses/add")
    public String saveExpense(Expense expense) {
        service.addExpense(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/{id}")
    public String GetUpdateExpense(ModelMap model, @PathVariable Long id) {
        model.addAttribute("expense", service.findById(id));
        model.addAttribute("title", "Відкорегувати витрату");
        return "/expenses/edit";
//        return "update_expense";
    }

    @PostMapping("/expenses/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute("expense") Expense expense) {
        service.update(id, expense);
        return "redirect:/expenses";
    }


}

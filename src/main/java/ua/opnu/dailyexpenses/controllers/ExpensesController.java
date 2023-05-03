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
        return "add_expense";
    }

//    @PostMapping("/expenses/add")
//    public String newExpense(@RequestParam String name,
//                             BigDecimal amount,
//                             String category,
//                             String description,
//                             LocalDate date
//                            ){
//        service.addExpense(new Expense(name, amount, category, description, date));
//        return "redirect:/expenses";
//    }

    @PostMapping("/expenses/add")
    public String saveExpense(Expense expense) {
        service.addExpense(expense);
        return "redirect:/expenses";
    }



}

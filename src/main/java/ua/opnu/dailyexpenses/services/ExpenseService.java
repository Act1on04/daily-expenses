package ua.opnu.dailyexpenses.services;

import org.springframework.stereotype.Service;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.repositories.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {

    private ExpenseRepository repository;

    public List<Expense> getExpensesList(){
        return repository.findAll();
    }

    public void addExpense(Expense expense) {
        repository.save(expense);
    }

}

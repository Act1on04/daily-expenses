package ua.opnu.dailyexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.repositories.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    public List<Expense> getExpensesList(){
        return repository.findAll();
    }

    public Expense addExpense(Expense expense) {
        repository.save(expense);
        return expense;
    }

    public Expense findById(Long id) {
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    public void delete(Long id) {
        Expense expense = findById(id);
        repository.delete(expense);
    }




}

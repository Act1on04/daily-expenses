package ua.opnu.dailyexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.repositories.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Override
    public List<Expense> getExpensesList(){
        return repository.findAll();
    }

    @Override
    public Expense findById(Long id) {
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public Expense addExpense(Expense expense) {
        repository.save(expense);
        return expense;
    }

    @Override
    public void update(Long id, Expense expense) {
        if(repository.findById(id).isPresent()){
            Expense updateExpense = repository.findById(id).get();

            updateExpense.setName(expense.getName());
            updateExpense.setAmount(expense.getAmount());
            updateExpense.setCategory(expense.getCategory());
            updateExpense.setDescription(expense.getDescription());
            updateExpense.setExpenseDate(expense.getExpenseDate());

            repository.save(updateExpense);
        }
    }

    @Override
    public void delete(Long id) {
        Expense expense = findById(id);
        repository.delete(expense);
    }




}

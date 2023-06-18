package ua.opnu.dailyexpenses.services;

import ua.opnu.dailyexpenses.models.Expense;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExpenseService {

    public List<Expense> getExpensesList();

    public Expense findById(Long id);

    public Expense addExpense(Expense expense, Long user_id);

    public void update(Long id, Expense expense);

    public void delete(Long id);

    public double getSumOfAmountFromDate(Long user_id, LocalDate fromDate);

}

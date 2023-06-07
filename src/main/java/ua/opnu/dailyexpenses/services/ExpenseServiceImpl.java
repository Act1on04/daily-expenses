package ua.opnu.dailyexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.dailyexpenses.controllers.UserController;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.repositories.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

//    @Autowired
    private final ExpenseRepository repository;

    private final UserController userController;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository repository, UserController userController) {
        this.repository = repository;
        this.userController = userController;
    }

    @Override
    public List<Expense> getExpensesList(){
//        return repository.findAllByUser_Id(userController.loggedUser.getId());
        return repository.findAllByUser_IdOrderByExpenseDateDescAmountDesc(userController.loggedUser.getId());
//        return repository.findAll(
//                Sort.by(Sort.Direction.DESC, "expenseDate")
//                .and(Sort.by(Sort.Direction.DESC, "amount"))
//        );
    }

    @Override
    public Expense findById(Long id) {
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public Expense addExpense(Expense expense, Long user_id) {
        expense.setUser(userController.loggedUser);
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
        repository.deleteById(id);
    }




}

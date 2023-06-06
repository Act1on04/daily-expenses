package ua.opnu.dailyexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.opnu.dailyexpenses.controllers.UserController;
import ua.opnu.dailyexpenses.models.Expense;
import ua.opnu.dailyexpenses.models.User;
import ua.opnu.dailyexpenses.repositories.ExpenseRepository;
import ua.opnu.dailyexpenses.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ExpenseServiceImpl implements ExpenseService {

//    @Autowired
    private final ExpenseRepository repository;
//    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserController UsrCntr;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Expense> getExpensesList(){
//        return repository.findAllByUser_IdOrderByExpenseDateDesc(UsrCntr.loggedUser.getId());
        return repository.findAll(
                Sort.by(Sort.Direction.DESC, "expenseDate")
                .and(Sort.by(Sort.Direction.DESC, "amount"))
        );
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
//        User user = Objects.requireNonNull(repository.findById(user_id).orElse(null)).getUser();
        expense.setUser(UsrCntr.loggedUser);
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

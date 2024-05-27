package ua.opnu.dailyexpenses.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.opnu.dailyexpenses.models.Expense;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query("SELECT u FROM Expense u WHERE u.user.id = :user_id ORDER BY u.expenseDate DESC , u.amount desc ")
    List<Expense> findAllByUser_Id(Long user_id);

    List<Expense> findAllByUser_IdOrderByExpenseDateDescAmountDesc(Long user_id);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user.id = :user_id AND e.expenseDate >= :fromDate ")
    double sumOfAmountFromDate(Long user_id, LocalDate fromDate);

}

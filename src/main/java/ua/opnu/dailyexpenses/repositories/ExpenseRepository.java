package ua.opnu.dailyexpenses.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.dailyexpenses.models.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Expense findAllByUser_IdEquals(Long user_id, Sort sort);

    Expense findAllByUser_IdOrderByExpenseDateDesc(Long user_id);

}

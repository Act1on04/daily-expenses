package ua.opnu.dailyexpenses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.dailyexpenses.models.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}

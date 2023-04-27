package ua.opnu.dailyexpenses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.dailyexpenses.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    
}

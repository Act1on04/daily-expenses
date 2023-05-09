package ua.opnu.dailyexpenses.services;

import ua.opnu.dailyexpenses.models.Category;
import ua.opnu.dailyexpenses.repositories.CategoryRepository;

import java.util.List;

public interface CategoryService {

    public List<Category> getCategoryList();

}

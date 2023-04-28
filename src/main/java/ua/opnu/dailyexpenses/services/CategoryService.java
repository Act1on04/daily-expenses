package ua.opnu.dailyexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import ua.opnu.dailyexpenses.models.Category;
import ua.opnu.dailyexpenses.repositories.CategoryRepository;

import java.util.List;

public class CategoryService {

//    @Autowired(required=false)
    private CategoryRepository repositoryCategory;

    public List<Category> getCategoryList(){
        return repositoryCategory.findAll();
    }



}

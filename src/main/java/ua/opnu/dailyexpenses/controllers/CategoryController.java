package ua.opnu.dailyexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.opnu.dailyexpenses.models.Category;
import ua.opnu.dailyexpenses.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

//    @Autowired(required=false)
    private CategoryService serviceCategory;

    @GetMapping("/list")
    public String categoryList(ModelMap model){
        model.put("expenses", serviceCategory.getCategoryList());
        return "list_category";
    }

    @GetMapping("/")
    public String newCategory(ModelMap model) {
        model.addAttribute("expense", new Category());
        return "add_category";
    }


}

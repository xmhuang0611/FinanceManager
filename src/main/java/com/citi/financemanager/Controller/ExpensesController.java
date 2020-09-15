package com.citi.financemanager.Controller;

import com.citi.financemanager.Entity.CategoriesEntity;
import com.citi.financemanager.Entity.ExpensesEntity;
import com.citi.financemanager.Service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping(value = "/details", produces = {"application/json", "application/xml"})
    public List<ExpensesEntity> getAllExpenses() {
        return expensesService.getAllExpenses();
    }

    @GetMapping(value = "/categories", produces = {"application/json", "application/xml"})
    public List<CategoriesEntity> getAllCategories() {
        return expensesService.getAllCategories();
    }

    @PutMapping(value = "/expenses_add", consumes = {"application/json", "application/xml"})
    public ResponseEntity addItemToExpenses(@RequestBody ExpensesEntity item) {
        expensesService.addItemsToExpenses(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/category_add", consumes = {"application/json", "application/xml"})
    public ResponseEntity addItemToCategory(@RequestBody CategoriesEntity item) {
        expensesService.addItemsToCategory(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") String id) {
        expensesService.deleteItemInCategories(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/expense_delete", consumes = {"application/json", "application/xml"})
    public ResponseEntity deleteExpenses(@RequestBody ExpensesEntity item) {
        expensesService.deleteItemInExpenses(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/expense_modify", consumes = {"application/json", "application/xml"})
    public ResponseEntity updateExpenses(@RequestBody ExpensesEntity item) {
        expensesService.updateItemInExpenses(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/category_modify", consumes = {"application/json", "application/xml"})
    public ResponseEntity modifyCategory(@RequestBody CategoriesEntity item) {
        expensesService.modifyItemInCategories(item);
        return ResponseEntity.ok().build();
    }


}

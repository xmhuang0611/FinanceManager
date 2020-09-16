package com.citi.financemanager.Service;


import com.citi.financemanager.Entity.CategoriesEntity;
import com.citi.financemanager.Entity.ExpensesEntity;

import java.util.List;
import java.util.Map;

public interface ExpensesService {
    public List<ExpensesEntity> getAllExpenses();

    public void addItemsToExpenses(ExpensesEntity expensesEntity);

    public void addItemsToCategory(CategoriesEntity categoriesEntity);

    public List<CategoriesEntity> getAllCategories();

    public void modifyItemInCategories(CategoriesEntity categoriesEntity);

    public void deleteItemInCategories(String id);

    public void deleteItemInExpenses(ExpensesEntity expensesEntity);


    public Map<String, Double> getCategoryExpense();

    public void updateExpense(ExpensesEntity expensesEntity);

}

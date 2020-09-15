package com.citi.financemanager.Service;


import com.citi.financemanager.Entity.CategoriesEntity;
import com.citi.financemanager.Entity.ExpensesEntity;

import java.util.List;

public interface ExpensesService {
    public List<ExpensesEntity> getAllExpenses();

    public void addItemsToExpenses(ExpensesEntity expensesEntity);

    public void addItemsToCategory(CategoriesEntity categoriesEntity);

    public List<CategoriesEntity> getAllCategories();

    public void modifyItemInCategories(CategoriesEntity categoriesEntity);

    public void deleteItemInCategories(String id);

    public void deleteItemInExpenses(ExpensesEntity expensesEntity);

    public void updateItemInExpenses(ExpensesEntity expensesEntity);
}

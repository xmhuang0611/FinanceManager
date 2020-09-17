package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.ExpenseCategory;
import com.citi.financemanager.Entity.ExpensesEntity;

import java.util.List;

public interface ExpensesEntityDao {
    public List<ExpensesEntity> getAllExpenses();

    public void addItemToExpenses(ExpensesEntity ExpensesEntity);

    public void deleteItemInExpenses(ExpensesEntity ExpensesEntity);

    public void updateItemInExpenses(ExpensesEntity ExpensesEntity);

    public double getTotalAccount();

    public List<ExpenseCategory> getCategoryExpense();

    public double getExpenses(String id);
}

package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.BudgetEntity;

import java.util.List;

public interface BudgetEntityDao {
    public List<BudgetEntity> getAllBudget();

    public void addItemToBudget(BudgetEntity budgetEntity);

    public void deleteBudgetItem(BudgetEntity budgetEntity);

    public void modifyBudgetItem(BudgetEntity budgetEntity);

    public double getCurrentBudget();

    public boolean ifContainsBudget();
}

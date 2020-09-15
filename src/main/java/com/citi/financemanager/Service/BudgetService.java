package com.citi.financemanager.Service;


import com.citi.financemanager.Entity.BudgetEntity;

import java.util.List;

public interface BudgetService {
    public List<BudgetEntity> getAllBudget();

    public void addItemToBudget(BudgetEntity budgetEntity);

    public void modifyBudget(BudgetEntity budgetEntity);

    public void deleteBudgetItem(BudgetEntity budgetEntity);
}

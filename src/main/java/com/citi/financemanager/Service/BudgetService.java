package com.citi.financemanager.Service;


import com.citi.financemanager.Entity.BudgetEntity;

import java.util.List;

public interface BudgetService {
    public List<BudgetEntity> getAllBudget();

    public boolean ifContainsBudget(BudgetEntity budgetEntity);

    public void modifyBudget(BudgetEntity budgetEntity);
}

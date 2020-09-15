package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.BudgetEntityDao;
import com.citi.financemanager.Entity.BudgetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    BudgetEntityDao budgetEntityDao;

    @Override
    public List<BudgetEntity> getAllBudget() {
        return budgetEntityDao.getAllBudget();
    }

    @Override
    public boolean ifContainsBudget(BudgetEntity budgetEntity) {
        return budgetEntityDao.ifContainsBudget(budgetEntity);
    }

    @Override
    public void modifyBudget(BudgetEntity budgetEntity) {
        budgetEntityDao.modifyBudgetItem(budgetEntity);
    }

    @Override
    public void addItemToBudget(BudgetEntity budgetEntity) {
        budgetEntityDao.addItemToBudget(budgetEntity);
    }

    @Override
    public void deleteBudgetItem(BudgetEntity budgetEntity) {
        budgetEntityDao.deleteBudgetItem(budgetEntity);
    }
}

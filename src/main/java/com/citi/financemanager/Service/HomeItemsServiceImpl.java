package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.AccountEntityDao;
import com.citi.financemanager.Dao.BudgetEntityDao;
import com.citi.financemanager.Dao.ExpensesEntityDao;
import com.citi.financemanager.Dao.IncomeEntityDao;
import com.citi.financemanager.Entity.HomeItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HomeItemsServiceImpl implements HomeItemsService {
    @Autowired
    ExpensesEntityDao expensesEntityDao;
    @Autowired
    IncomeEntityDao incomeEntityDao;
    @Autowired
    BudgetEntityDao budgetEntityDao;
    @Autowired
    AccountEntityDao accountEntityDao;


    @Override
    public HomeItems getHomeItems() {
        double expenses, incomeValue, budget, accountValue;
        expenses = expensesEntityDao.getTotalAccout();
        incomeValue = incomeEntityDao.getTotalIncome();
        budget = budgetEntityDao.getCurrentBudget();
        accountValue = accountEntityDao.getAccountValue();

        HomeItems homeItems = new HomeItems(expenses, incomeValue, budget, accountValue);
        return homeItems;
    }
}

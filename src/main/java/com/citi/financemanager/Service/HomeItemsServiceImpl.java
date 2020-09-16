package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.AccountEntityDao;
import com.citi.financemanager.Dao.BudgetEntityDao;
import com.citi.financemanager.Dao.ExpensesEntityDao;
import com.citi.financemanager.Dao.IncomeEntityDao;
import com.citi.financemanager.Entity.HomeItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


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
        double expensesValue, incomeValue, budget, accountValue;
        expensesValue = expensesEntityDao.getTotalAccount();
        BigDecimal b = new BigDecimal(expensesValue);
        expensesValue = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        incomeValue = incomeEntityDao.getTotalIncome();
        BigDecimal c = new BigDecimal(incomeValue);
        incomeValue = c.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        budget = budgetEntityDao.getCurrentBudget();
        BigDecimal d = new BigDecimal(budget);
        budget = d.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        accountValue = accountEntityDao.getAccountValue();
        BigDecimal e = new BigDecimal(accountValue);
        accountValue = e.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        return new HomeItems(expensesValue, incomeValue, budget, accountValue);
    }
}

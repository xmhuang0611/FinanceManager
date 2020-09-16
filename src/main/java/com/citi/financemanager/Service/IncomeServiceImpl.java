package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.AccountEntityDao;
import com.citi.financemanager.Dao.IncomeEntityDao;
import com.citi.financemanager.Entity.IncomeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    IncomeEntityDao incomeEntityDao;
    @Autowired
    AccountEntityDao accountEntityDao;

    @Override
    public List<IncomeEntity> getIncomesList() {
        return incomeEntityDao.getAllIncome();
    }

    @Override
    public void addIncome(IncomeEntity incomeEntity) {
        incomeEntityDao.addItemToIncome(incomeEntity);
        double account = accountEntityDao.getAccountValue();
        account += incomeEntity.getValue();
        accountEntityDao.updateAccount(account);
    }

    @Override
    public void updateItemInIncome(IncomeEntity incomeEntity) {
        incomeEntityDao.updateItemInIncome(incomeEntity);
    }

    @Override
    public void deleteItemInIncome(IncomeEntity incomeEntity) {
        incomeEntityDao.deleteItemInIncome(incomeEntity);
        double account = accountEntityDao.getAccountValue();
        account -= incomeEntity.getValue();
        accountEntityDao.updateAccount(account);
    }

    @Override
    public double getTotalIncome() {
        return incomeEntityDao.getTotalIncome();
    }

    @Override
    public void updateIncome(IncomeEntity incomeEntity) {
        String id = incomeEntity.getId();
        double pre_income = incomeEntityDao.getIncome(id);
        incomeEntityDao.updateItemInIncome(incomeEntity);
        double new_income = incomeEntity.getValue();
        double account = accountEntityDao.getAccountValue();
        account = account - pre_income + new_income;
        accountEntityDao.updateAccount(account);
    }
}

package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.AccountEntityDao;
import com.citi.financemanager.Dao.CategoriesEntityDao;
import com.citi.financemanager.Dao.ExpensesEntityDao;
import com.citi.financemanager.Entity.CategoriesEntity;
import com.citi.financemanager.Entity.ExpensesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    ExpensesEntityDao expensesEntityDao;
    @Autowired
    CategoriesEntityDao categoriesEntityDao;
    @Autowired
    AccountEntityDao accountEntityDao;

    @Override
    public List<ExpensesEntity> getAllExpenses() {
        return expensesEntityDao.getAllExpenses();
    }

    @Override
    public void addItemsToExpenses(ExpensesEntity expensesEntity) {
        expensesEntityDao.addItemToExpenses(expensesEntity);
        double account = accountEntityDao.getAccountValue();
        account -= expensesEntity.getValue();
        accountEntityDao.updateAccount(account);
    }

    @Override
    public void addItemsToCategory(CategoriesEntity categoriesEntity) {
        categoriesEntityDao.addItemToCategories(categoriesEntity);
    }

    @Override
    public void modifyItemInCategories(CategoriesEntity categoriesEntity) {
        categoriesEntityDao.updateItemInCategories(categoriesEntity);
    }

    @Override
    public void updateItemInExpenses(ExpensesEntity expensesEntity) {
        expensesEntityDao.updateItemInExpenses(expensesEntity);
    }

    @Override
    public void deleteItemInCategories(String id) {
        categoriesEntityDao.deleteItemInCategories(id);
    }

    @Override
    public void deleteItemInExpenses(ExpensesEntity expensesEntity) {
        expensesEntityDao.deleteItemInExpenses(expensesEntity);
    }

    @Override
    public List<CategoriesEntity> getAllCategories() {
        return categoriesEntityDao.getAllCategories();
    }

}

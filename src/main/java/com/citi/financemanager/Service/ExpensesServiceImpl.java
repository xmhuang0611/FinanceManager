package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.AccountEntityDao;
import com.citi.financemanager.Dao.CategoriesEntityDao;
import com.citi.financemanager.Dao.ExpensesEntityDao;
import com.citi.financemanager.Entity.CategoriesEntity;
import com.citi.financemanager.Entity.ExpenseCategory;
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
    public void deleteItemInCategories(String id) {
        categoriesEntityDao.deleteItemInCategories(id);
    }

    @Override
    public void deleteItemInExpenses(ExpensesEntity expensesEntity) {
        expensesEntityDao.deleteItemInExpenses(expensesEntity);
        double account = accountEntityDao.getAccountValue();
        account += expensesEntity.getValue();
        accountEntityDao.updateAccount(account);
    }

    @Override
    public List<CategoriesEntity> getAllCategories() {
        return categoriesEntityDao.getAllCategories();
    }

    @Override
    public List<ExpenseCategory> getCategoryExpense() {
        return expensesEntityDao.getCategoryExpense();
    }

    @Override
    public void updateExpense(ExpensesEntity expensesEntity) {
        String id = expensesEntity.getId();
        double pre_expenses = expensesEntityDao.getExpenses(id);
        expensesEntityDao.updateItemInExpenses(expensesEntity);
        double new_expenses = expensesEntity.getValue();
        double account = accountEntityDao.getAccountValue();
        account = account + pre_expenses - new_expenses;
        accountEntityDao.updateAccount(account);
    }

}

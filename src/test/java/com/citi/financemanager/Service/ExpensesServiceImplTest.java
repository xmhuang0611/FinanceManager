package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.AccountEntityDaoImpl;
import com.citi.financemanager.Dao.CategoriesEntityDaoImpl;
import com.citi.financemanager.Dao.ExpensesEntityDaoImpl;
import com.citi.financemanager.Entity.AccountEntity;
import com.citi.financemanager.Entity.CategoriesEntity;
import com.citi.financemanager.Entity.ExpensesEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ExpensesServiceImplTest {

    @InjectMocks
    ExpensesServiceImpl expensesService;

    @Mock
    ExpensesEntityDaoImpl expensesEntityDao;
    @Mock
    CategoriesEntityDaoImpl categoriesEntityDao;
    @Mock
    AccountEntityDaoImpl accountEntityDao;


    ExpensesEntity expensesEntity = new ExpensesEntity("5f5f3bd8215a71b8e60e64ab", "Utilitie", 88.88, "2020-9-10", "this is for test");
    List<ExpensesEntity> expenseslist = new ArrayList<>();
    CategoriesEntity categoriesEntity = new CategoriesEntity("5f5f37d4215a71b8e60e64a2", "Utilities");
    AccountEntity accountEntity = new AccountEntity("5f5f3c23215a71b8e60e64ca", 986.75);
    List<AccountEntity> ac = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllExpenses() {
        expenseslist.add(expensesEntity);
        Mockito.when(expensesEntityDao.getAllExpenses()).thenReturn(expenseslist);
        assertEquals(expenseslist, expensesService.getAllExpenses());
    }

    @Test
    public void addItemsToExpenses() {
        expensesService.addItemsToExpenses(expensesEntity);
        verify(expensesEntityDao, times(1)).addItemToExpenses(expensesEntity);
    }

    @Test
    public void addItemsToCategory() {
        expensesService.addItemsToCategory(categoriesEntity);
        verify(categoriesEntityDao).addItemToCategories(categoriesEntity);
    }

    @Test
    public void modifyItemInCategories() {
        expensesService.modifyItemInCategories(categoriesEntity);
        verify(categoriesEntityDao).updateItemInCategories(categoriesEntity);
    }

    @Test
    public void deleteItemInCategories() {
        expensesService.deleteItemInCategories("5f5f37d4215a71b8e60e64a2");
        verify(categoriesEntityDao).deleteItemInCategories("5f5f37d4215a71b8e60e64a2");
    }

    @Test
    public void deleteItemInExpenses() {
        expensesService.deleteItemInExpenses(expensesEntity);
        verify(expensesEntityDao).deleteItemInExpenses(expensesEntity);
    }

    @Test
    public void getAllCategories() {
        expensesService.getAllCategories();
        verify(categoriesEntityDao).getAllCategories();
    }

    @Test
    public void getCategoryExpense() {
        expensesService.getCategoryExpense();
        verify(expensesEntityDao).getCategoryExpense();
    }

    @Test
    public void updateExpense() {
        expensesService.updateExpense(expensesEntity);
        verify(expensesEntityDao).updateItemInExpenses(expensesEntity);
    }
}

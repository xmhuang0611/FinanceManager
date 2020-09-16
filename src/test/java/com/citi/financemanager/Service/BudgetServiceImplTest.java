package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.BudgetEntityDaoImpl;
import com.citi.financemanager.Entity.BudgetEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class BudgetServiceImplTest {

    @InjectMocks
    BudgetServiceImpl budgetService;
    @Mock
    BudgetEntityDaoImpl budgetEntityDao;

    BudgetEntity budgetEntity = new BudgetEntity("5f5f3bfb215a71b8e60e64bf", "2021-10", 100.00);
    List<BudgetEntity> budgets = new ArrayList<>();


    @org.junit.Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void getAllBudget() {
        budgets.add(budgetEntity);
        Mockito.when(budgetEntityDao.getAllBudget()).thenReturn(budgets);
        assertEquals(budgets, budgetService.getAllBudget());
    }

    @org.junit.Test
    public void addItemToBudget() {
        budgetService.addItemToBudget(budgetEntity);
        verify(budgetEntityDao).addItemToBudget(budgetEntity);
    }

    @org.junit.Test
    public void modifyBudget() {
        budgetService.modifyBudget(budgetEntity);
        verify(budgetEntityDao).modifyBudgetItem(budgetEntity);
    }

    @org.junit.Test
    public void deleteBudgetItem() {
        budgets.add(budgetEntity);
        budgetService.deleteBudgetItem(budgetEntity);
        verify(budgetEntityDao).deleteBudgetItem(budgetEntity);
    }
}

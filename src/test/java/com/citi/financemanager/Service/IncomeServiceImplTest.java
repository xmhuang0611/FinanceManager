package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.AccountEntityDaoImpl;
import com.citi.financemanager.Dao.BudgetEntityDaoImpl;
import com.citi.financemanager.Dao.IncomeEntityDaoImpl;
import com.citi.financemanager.Entity.AccountEntity;
import com.citi.financemanager.Entity.BudgetEntity;
import com.citi.financemanager.Entity.IncomeEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IncomeServiceImplTest {

    @InjectMocks
    IncomeServiceImpl incomeService;
    @Mock
    IncomeEntityDaoImpl incomeEntityDao;
    @Mock
    AccountEntityDaoImpl accountEntityDao;

    IncomeEntity incomeEntity = new IncomeEntity("5f5f3bfb215a71ffe60e64bf","2020-5-5",55.00,"for test");
    List<IncomeEntity> incomes = new ArrayList<>();
    AccountEntity accountEntity = new AccountEntity("5f5f3c23215a71b8e60e64ca", 986.75);
    List<AccountEntity> ac = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getIncomesList() {
        incomes.add(incomeEntity);
        Mockito.when(incomeEntityDao.getAllIncome()).thenReturn(incomes);
        assertEquals(incomes, incomeService.getIncomesList());
    }

    @Test
    public void addIncome() {
        incomeService.addIncome(incomeEntity);
        verify(incomeEntityDao).addItemToIncome(incomeEntity);
    }

    @Test
    public void updateItemInIncome() {
        incomeService.updateItemInIncome(incomeEntity);
        verify(incomeEntityDao).updateItemInIncome(incomeEntity);
    }

    @Test
    public void deleteItemInIncome() {
        incomeService.deleteItemInIncome(incomeEntity);
        verify(incomeEntityDao).deleteItemInIncome(incomeEntity);
    }

    @Test
    public void getTotalIncome() {
        incomeService.getTotalIncome();
        verify(incomeEntityDao).getTotalIncome();
    }

    @Test
    public void updateIncome() {
        incomeService.updateIncome(incomeEntity);
        verify(incomeEntityDao).updateItemInIncome(incomeEntity);
    }
}
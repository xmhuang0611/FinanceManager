package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.*;
import com.citi.financemanager.Entity.AccountEntity;
import com.citi.financemanager.Entity.HomeItems;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomeItemsServiceImplTest {

    @InjectMocks
    HomeItemsServiceImpl homeItemsService;
    //    @InjectMocks
//    ExpensesServiceImpl expensesService;
//    @InjectMocks
//    BudgetServiceImpl budgetService;
    @Mock
    IncomeEntityDaoImpl incomeEntityDao;
    @Mock
    AccountEntityDaoImpl accountEntityDao;
    @Mock
    BudgetEntityDaoImpl budgetEntityDao;
    @Mock
    ExpensesEntityDaoImpl expensesEntityDao;
    @Mock
    CategoriesEntityDaoImpl categoriesEntityDao;
    //    @Mock
//    ExpensesEntity expensesEntity;
//    @Mock
//    IncomeEntity incomeEntity;
//    @Mock
//    AccountEntity accountEntity;
//    @Mock
//    BudgetEntity budgetEntity;
    @Mock
    HomeItems homeItems = new HomeItems(11.11, 22.22, 20.00, 11.11);
    //    @Mock
//    CategoriesEntity categoriesEntity;
    AccountEntity accountEntity = new AccountEntity("5f5f3c23215a71b8e60e64ca", 986.75);
    List<AccountEntity> ac = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getHomeItems() {
        ac.add(accountEntity);
        Mockito.when(homeItemsService.getHomeItems()).thenReturn(homeItems);
        assertEquals(homeItems, homeItemsService.getHomeItems());

    }
}

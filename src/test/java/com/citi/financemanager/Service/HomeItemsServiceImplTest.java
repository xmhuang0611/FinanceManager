package com.citi.financemanager.Service;

import com.citi.financemanager.Dao.*;
import com.citi.financemanager.Entity.HomeItems;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class HomeItemsServiceImplTest {

    @InjectMocks
    HomeItemsServiceImpl homeItemsService;
    @Mock
    ExpensesServiceImpl expensesService;
    @Mock
    BudgetServiceImpl budgetService;
    @Mock
    IncomeEntityDaoImpl incomeEntityDao;
    @Mock
    IncomeServiceImpl incomeService;
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
//    //    @Mock
////    AccountEntity accountEntity;
//    @Mock
//    BudgetEntity budgetEntity;
//    @Mock
//    CategoriesEntity categoriesEntity;
//    HomeItems mock = Mockito.mock(HomeItems.class);
//    AccountEntity accountEntity = new AccountEntity("5f5f3c23215a71b8e60e64ca", 986.75);
//    List<AccountEntity> ac = new ArrayList<>();

    @Mock
    HomeItems homeItems;

    double accountValue, expensesValue, budgets, incomeValue;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getHomeItems() {
        homeItemsService.getHomeItems();
        verify(accountEntityDao).getAccountValue();
        verify(budgetEntityDao).getCurrentBudget();
        verify(incomeEntityDao).getTotalIncome();
        verify(expensesEntityDao).getTotalAccount();


    }
}

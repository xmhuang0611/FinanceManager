package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.IncomeEntity;

import java.util.List;

public interface IncomeEntityDao {
    public List<IncomeEntity> getAllIncome();

    public void addItemToIncome(IncomeEntity IncomeEntity);

    public void deleteItemInIncome(IncomeEntity incomeEntity);

    public void updateItemInIncome(IncomeEntity incomeEntity);

    public double getTotalIncome();
}

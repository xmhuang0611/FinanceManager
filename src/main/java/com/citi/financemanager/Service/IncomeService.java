package com.citi.financemanager.Service;


import com.citi.financemanager.Entity.IncomeEntity;

import java.util.List;

public interface IncomeService {
    public List<IncomeEntity> getIncomesList();

    public void addIncome(IncomeEntity incomeEntity);

    public void updateItemInIncome(IncomeEntity incomeEntity);

    public void deleteItemInIncome(IncomeEntity incomeEntity);

    public double getTotalIncome();
}

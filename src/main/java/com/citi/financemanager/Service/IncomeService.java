package com.citi.financemanager.Service;


import com.citi.financemanager.Entity.IncomeEntity;

import java.util.List;

public interface IncomeService {
    public List<IncomeEntity> getIncomesList();

    public void addIncome(IncomeEntity incomeEntity);
}

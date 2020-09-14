package com.citi.financemanager.Entity;

public class HomeItems {
    private double expensesValue;
    private double incomeValue;
    private double budget;
    private double accountValue;

    public HomeItems(double expensesValue, double incomeValue, double budget, double accountValue) {
        this.expensesValue = expensesValue;
        this.incomeValue = incomeValue;
        this.budget = budget;
        this.accountValue = accountValue;
    }

    public double getExpensesValue() {
        return expensesValue;
    }

    public void setExpensesValue(double expensesValue) {
        this.expensesValue = expensesValue;
    }

    public double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(double accountValue) {
        this.accountValue = accountValue;
    }
}

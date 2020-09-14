package com.citi.financemanager.Entity;

public class BudgetEntity {
    private int id;
    private String dateByMonth;
    private double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateByMonth() {
        return dateByMonth;
    }

    public void setDateByMonth(String dateByMonth) {
        this.dateByMonth = dateByMonth;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

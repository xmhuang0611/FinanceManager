package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.ExpenseCategory;
import com.citi.financemanager.Entity.ExpensesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExpensesEntityDaoImpl implements ExpensesEntityDao {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExpensesEntityDaoImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public List<ExpensesEntity> getAllExpenses() {
        return mongoTemplate.findAll(ExpensesEntity.class, "expense");
    }

    public void addItemToExpenses(ExpensesEntity expensesEntity) {
        mongoTemplate.insert(expensesEntity);

    }

    public void deleteItemInExpenses(ExpensesEntity expensesEntity) {
        mongoTemplate.remove(expensesEntity);
    }

    public void updateItemInExpenses(ExpensesEntity expensesEntity) {
        mongoTemplate.save(expensesEntity);
    }

    public double getTotalAccount() {
        List<ExpensesEntity> result = mongoTemplate.findAll(ExpensesEntity.class);
        double totalValue = 0.00;
        for (ExpensesEntity es : result) {
            totalValue += es.getValue();
        }
        return totalValue;
    }

    public List<ExpenseCategory> getCategoryExpense() {
        Map<String, Double> map = new HashMap<>();
        List<ExpensesEntity> result = mongoTemplate.findAll(ExpensesEntity.class);
        for (ExpensesEntity es : result) {
            if (map.containsKey(es.getCategoryName())) {
                BigDecimal a = new BigDecimal(map.get(es.getCategoryName()) + es.getValue());
                double val = a.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                map.put(es.getCategoryName(), val);
            } else {
                BigDecimal a = new BigDecimal(es.getValue());
                double val = a.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                map.put(es.getCategoryName(), val);
            }
        }
        List<ExpenseCategory> ec = new ArrayList<>();
        for (String cate : map.keySet()) {
            ExpenseCategory newExpensesCategory = new ExpenseCategory();
            newExpensesCategory.setName(cate);
            newExpensesCategory.setVal(map.get(cate));
            ec.add(newExpensesCategory);
        }
        return ec;

    }


    public double getExpenses(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        ExpensesEntity expensesEntity = mongoTemplate.find(query, ExpensesEntity.class).get(0);
        return expensesEntity.getValue();
    }
}

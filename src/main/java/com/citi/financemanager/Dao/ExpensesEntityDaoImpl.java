package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.ExpensesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpensesEntityDaoImpl implements ExpensesEntityDao {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExpensesEntityDaoImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public List<ExpensesEntity> getAllExpenses() {
        return mongoTemplate.findAll(ExpensesEntity.class, "expenses");
    }

    public void addItemToExpenses(ExpensesEntity expensesEntity) {
        mongoTemplate.insert(expensesEntity);
    }

    public void deleteItemInExpenses(ExpensesEntity expensesEntity) {
        mongoTemplate.remove(expensesEntity);
    }

    public void updateItemInExpenses(ExpensesEntity expensesEntity) {
        Query query = new Query(Criteria.where("_id").is(expensesEntity.getId()));
        Update update = new Update();
        update.set("value", expensesEntity.getValue());
        update.set("categoryId", expensesEntity.getCategoryName());
        update.set("date", expensesEntity.getDate());
        update.set("description", expensesEntity.getDescription());

        mongoTemplate.updateFirst(query, update, ExpensesEntity.class);
    }

    public double getTotalAccount() {
        List<ExpensesEntity> result = mongoTemplate.findAll(ExpensesEntity.class);
        double totalValue = 0;
        for (ExpensesEntity es : result) {
            totalValue += es.getValue();
        }
        return totalValue;
    }
}

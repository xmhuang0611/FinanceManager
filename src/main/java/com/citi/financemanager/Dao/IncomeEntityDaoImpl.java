package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.IncomeEntity;
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

public class IncomeEntityDaoImpl implements IncomeEntityDao {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(IncomeEntityDaoImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public List<IncomeEntity> getAllIncome() {
        return mongoTemplate.findAll(IncomeEntity.class);
    }

    public void addItemToIncome(IncomeEntity incomeEntity) {
        mongoTemplate.insert(incomeEntity);
    }

    public void deleteItemInIncome(IncomeEntity incomeEntity) {
        Query query = new Query(Criteria.where("_id").is(incomeEntity.getId()));
        mongoTemplate.remove(query, "income");
    }

    public void updateItemInIncome(IncomeEntity incomeEntity) {
        Query query = new Query(Criteria.where("_id").is(incomeEntity.getId()));
        Update update = new Update();
        update.set("value", incomeEntity.getValue());
        update.set("date", incomeEntity.getDate());
        update.set("description", incomeEntity.getDescription());
        mongoTemplate.updateFirst(query, update, IncomeEntity.class);

    }

    public double getTotalIncome() {
        List<IncomeEntity> result = mongoTemplate.findAll(IncomeEntity.class);
        double totalValue = 0;
        for (IncomeEntity ie : result) {
            totalValue += ie.getValue();
        }
        return totalValue;
    }

    public double getIncome(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        IncomeEntity incomeEntity = mongoTemplate.find(query, IncomeEntity.class).get(0);
        return incomeEntity.getValue();
    }
}

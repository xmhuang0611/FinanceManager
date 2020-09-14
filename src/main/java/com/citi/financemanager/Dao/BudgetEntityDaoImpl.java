package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.BudgetEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BudgetEntityDaoImpl implements BudgetEntityDao {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(BudgetEntityDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<BudgetEntity> getAllBudget() {
        return mongoTemplate.findAll(BudgetEntity.class);
    }


    public void addItemToBudget(BudgetEntity budgetEntity) {
        mongoTemplate.insert(budgetEntity);
    }


    public void deleteBudgetItem(BudgetEntity budgetEntity) {
        mongoTemplate.remove(budgetEntity);
    }


    public void modifyBudgetItem(BudgetEntity budgetEntity) {
        mongoTemplate.update(BudgetEntity.class);
    }

    public double getCurrentBudget() {
        return mongoTemplate.findAll(BudgetEntity.class).get(0).getValue();
    }

    public boolean ifContainsBudget() {
        return mongoTemplate.findAll(BudgetEntity.class).isEmpty();
    }
}

package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.BudgetEntity;
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
        Query query = new Query(Criteria.where("dateByMonth").is(budgetEntity.getDateByMonth()));
        mongoTemplate.remove(query,BudgetEntity.class);
    }


    public void modifyBudgetItem(BudgetEntity budgetEntity) {
        Query query = new Query(Criteria.where("dateByMonth").is(budgetEntity.getDateByMonth()));
        Update update = new Update();
        update.set("value", budgetEntity.getValue());
        mongoTemplate.updateFirst(query, update, BudgetEntity.class);
    }

    public double getCurrentBudget() {
        return mongoTemplate.findAll(BudgetEntity.class).get(0).getValue();
    }

    public boolean ifContainsBudget(BudgetEntity budgetEntity) {
        Query query = new Query(Criteria.where("dateByMonth").is(budgetEntity.getDateByMonth()));
        return mongoTemplate.findOne(query,BudgetEntity.class)!=null;
    }
}

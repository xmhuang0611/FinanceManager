package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.AccountEntity;
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

public class AccountEntityDaoImpl implements AccountEntityDao {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AccountEntityDaoImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public double getAccountValue() {
        List<AccountEntity> ac = mongoTemplate.findAll(AccountEntity.class);
        return ac.get(0).getValue();
    }

    public double updateAccount(double value) {
        List<AccountEntity> ac = mongoTemplate.findAll(AccountEntity.class);
        String id = ac.get(0).getId();
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = Update.update("value", value);
        mongoTemplate.updateFirst(query, update, AccountEntity.class);
        return 0;
    }
}

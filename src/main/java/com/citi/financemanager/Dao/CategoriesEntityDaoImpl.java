package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.CategoriesEntity;
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
public class CategoriesEntityDaoImpl implements CategoriesEntityDao {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CategoriesEntityDaoImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public List<CategoriesEntity> getAllCategories() {
        return mongoTemplate.findAll(CategoriesEntity.class);
    }

    public void addItemToCategories(CategoriesEntity categoriesEntity) {
        mongoTemplate.insert(categoriesEntity);
    }

    public void deleteItemInCategories(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, "category");
    }

    public void updateItemInCategories(CategoriesEntity categoriesEntity) {
        mongoTemplate.updateFirst(
                new Query(Criteria.where("_id").is(categoriesEntity.getId())),
                new Update().set("name", categoriesEntity.getName()),
                CategoriesEntity.class);

    }

    public boolean ifCategoryContainsId(String id) {
        return mongoTemplate.find(new Query(Criteria.where("_id").is(id)), CategoriesEntity.class).isEmpty();
    }
}


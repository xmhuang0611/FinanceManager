package com.citi.financemanager.Dao;

import com.citi.financemanager.Entity.CategoriesEntity;

import java.util.List;

public interface CategoriesEntityDao {
    public List<CategoriesEntity> getAllCategories();

    public void addItemToCategories(CategoriesEntity categoriesEntity);

    public void deleteItemInCategories(String id);

    public void updateItemInCategories(CategoriesEntity categoriesEntity);

    public boolean ifCategoryContainsId(String id);
}

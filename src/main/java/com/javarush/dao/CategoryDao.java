package com.javarush.dao;

import com.javarush.entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDao extends AbstractDao<Category> {
    public CategoryDao(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}

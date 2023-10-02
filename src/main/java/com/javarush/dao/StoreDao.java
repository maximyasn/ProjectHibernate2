package com.javarush.dao;

import com.javarush.entity.Store;
import org.hibernate.SessionFactory;

public class StoreDao extends AbstractDao<Store> {
    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}

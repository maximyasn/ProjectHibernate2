package com.javarush.dao;

import com.javarush.entity.City;
import org.hibernate.SessionFactory;

public class CityDao extends AbstractDao<City>{
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}

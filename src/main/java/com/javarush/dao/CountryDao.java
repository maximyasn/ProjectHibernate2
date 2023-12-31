package com.javarush.dao;

import com.javarush.entity.Country;
import org.hibernate.SessionFactory;

public class CountryDao extends AbstractDao<Country> {
    public CountryDao( SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}

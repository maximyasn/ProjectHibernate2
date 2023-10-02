package com.javarush.dao;

import com.javarush.entity.Rating;
import org.hibernate.SessionFactory;

public class RatingDao extends AbstractDao<Rating> {
    public RatingDao(SessionFactory sessionFactory) {
        super(Rating.class, sessionFactory);
    }
}

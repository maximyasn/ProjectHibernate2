package com.javarush.dao;

import com.javarush.entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalDao extends AbstractDao<Rental> {
    public RentalDao(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getUnreturnedItem() {
        Query<Rental> query = getCurrentSession()
                .createQuery("select r from Rental r where returnDate is null", Rental.class);
        query.setMaxResults(1);

        return query.getSingleResult();

    }
}

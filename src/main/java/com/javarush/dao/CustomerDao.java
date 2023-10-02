package com.javarush.dao;

import com.javarush.entity.Customer;
import com.javarush.entity.Film;
import org.hibernate.SessionFactory;

public class CustomerDao extends AbstractDao<Customer> {
    public CustomerDao(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }

    public Customer getById(Short id) {
        return getCurrentSession().get(Customer.class, id);
    }
}

package com.javarush.dao;

import com.javarush.entity.Address;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Random;

public class AddressDao extends AbstractDao<Address> {
    private Short count;
    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }

    public Address getRandomAddress() {
        count = (short) new Random().nextInt(500);
        Query<Address> addressQuery = getCurrentSession()
                .createQuery("select a from Address a where a.id = :ID", Address.class);
        addressQuery.setParameter("ID", count);
        return addressQuery.getSingleResult();
    }
}

package com.javarush.dao;

import com.javarush.entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDao extends AbstractDao<Film> {
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFilmFromTable() {
        Query<Film> query = getCurrentSession()
                .createQuery("select f from Film f where f.id " +
                             "not in (select distinct film.id from Inventory)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}

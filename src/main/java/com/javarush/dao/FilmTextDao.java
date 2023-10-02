package com.javarush.dao;

import com.javarush.entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDao extends AbstractDao<FilmText> {
    public FilmTextDao(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}

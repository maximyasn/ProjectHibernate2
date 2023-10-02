package com.javarush.dao;

import com.javarush.entity.Language;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LanguageDao extends AbstractDao<Language> {
    public LanguageDao(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }

}

package com.javarush.dao;

import com.javarush.entity.Film;
import com.javarush.entity.Inventory;
import com.javarush.entity.Store;
import org.hibernate.SessionFactory;

public class InventoryDao extends AbstractDao<Inventory> {
    public InventoryDao(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public Inventory createInventory(Film film, Store store) {
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);
        return inventory;
    }
}

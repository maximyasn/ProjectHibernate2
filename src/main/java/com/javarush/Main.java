package com.javarush;

import com.javarush.dao.*;
import com.javarush.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private final SessionFactory sessionFactory;

    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final CustomerDao customerDao;
    private final FilmDao filmDao;
    private final FilmTextDao filmTextDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RatingDao ratingDao;
    private final RentalDao rentalDao;
    private final StaffDao staffDao;
    private final StoreDao storeDao;

    public Main() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Feature.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rating.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .configure();

        sessionFactory = configuration.buildSessionFactory();

        actorDao = new ActorDao(sessionFactory);
        addressDao = new AddressDao(sessionFactory);
        categoryDao = new CategoryDao(sessionFactory);
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        filmDao = new FilmDao(sessionFactory);
        filmTextDao = new FilmTextDao(sessionFactory);
        inventoryDao = new InventoryDao(sessionFactory);
        languageDao = new LanguageDao(sessionFactory);
        paymentDao = new PaymentDao(sessionFactory);
        ratingDao = new RatingDao(sessionFactory);
        rentalDao = new RentalDao(sessionFactory);
        staffDao = new StaffDao(sessionFactory);
        storeDao = new StoreDao(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.returnFilm();
    }


    private Customer createCustomer(String name, String lastname, String email) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Customer customer = new Customer();

            Store store = storeDao.getItems(0, 1).get(0);
            Address address = addressDao.getRandomAddress();
            customer.setStore(store);
            customer.setFirstName(name);
            customer.setLastName(lastname);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setIsActive(true);

            customerDao.save(customer);

            session.getTransaction().commit();

            return customer;
        }
    }

    private void returnFilm() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

           Rental rental = rentalDao.getUnreturnedItem();
           rental.setReturnDate(LocalDateTime.now());

           rentalDao.save(rental);

           session.getTransaction().commit();
        }
    }

    private void customerRentedInventory() {
        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            Film film = filmDao.getFilmFromTable();
            Store store = storeDao.getItems(0, 1).get(0);

            Short num = 1;
            Customer customer = customerDao.getById(num);

            Inventory inventory = inventoryDao.createInventory(film, store);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDao.save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(110.00));
            payment.setStaff(staff);
            paymentDao.save(payment);

            session.getTransaction().commit();
        }
    }
    private void showNewFilm() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDao.getItems(0, 6)
                    .stream()
                    .unordered()
                    .findFirst()
                    .get();

            List<Category> categories = categoryDao.getItems(0, 5);
            List<Actor> actors = actorDao.getItems(0, 20);

            Film film = new Film();
            film.setTitle("New Film");
            film.setDescription("Description for the New Film");
            film.setYear(Year.now());
            film.setLanguageId(language);
            film.setOriginalLanguageId(language);
            film.setRating(Rating.NC_17);
            film.setActors(new HashSet<>(actors));
            film.setSpecialFeatures(Set.of(Feature.DELETED_SCENES, Feature.TRAILERS));
            film.setReplacementCost(BigDecimal.TEN);
            film.setRentalRate(BigDecimal.ONE);
            film.setLength((short)100);
            film.setRentalDuration((byte) 30);
            film.setCategories(new HashSet<>(categories));
            filmDao.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setDescription("Description for the New Film");
            filmText.setTitle("New Film");
            filmText.setId(film.getId());

            filmTextDao.save(filmText);



            session.getTransaction().commit();
        }
    }
}
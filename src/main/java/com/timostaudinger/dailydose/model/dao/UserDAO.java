package com.timostaudinger.dailydose.model.dao;

import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.util.Frequency;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDAO {
    private Session session;

    public UserDAO() {
        session = Database.getSessionFactory().getCurrentSession();
    }

    public void save(User user) {
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    public List<User> findAll() {
        session.beginTransaction();
        Query query = session.createQuery("from com.timostaudinger.dailydose.model.dto.User");
        List<User> users = query.list();
        session.getTransaction().commit();
        return users;
    }

    public List<User> findAll(Frequency frequency) {
        session.beginTransaction();
        Query query = session.createQuery("from com.timostaudinger.dailydose.model.dto.User where frequency = :frequency");
        query.setParameter("frequency", frequency);
        List<User> users = query.list();
        session.getTransaction().commit();
        return users;
    }
}

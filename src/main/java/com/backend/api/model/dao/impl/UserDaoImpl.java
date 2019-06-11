package com.backend.api.model.dao.impl;

import com.backend.api.model.dao.IuserDao;
import com.backend.api.model.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IuserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User addorUpdateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        return user;
    }

    @Override
    public User getUser(String username) {

        Session session = sessionFactory.getCurrentSession();
        Query qry = session.createQuery("from User where email=:username");
        qry.setParameter("username", username);
        User user = (User) qry.uniqueResult();

        return user;
    }

    @Override
    public Integer userEnable(int userId, boolean enable) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update User set enable=:enable where id=:userId");
        query.setParameter("enable", enable);
        query.setParameter("userId", userId);
        return query.executeUpdate();

    }
}

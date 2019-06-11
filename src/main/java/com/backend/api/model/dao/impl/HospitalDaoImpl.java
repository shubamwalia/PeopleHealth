package com.backend.api.model.dao.impl;

import com.backend.api.model.dao.IhospitalDao;
import com.backend.api.model.entity.Doctor;
import com.backend.api.model.entity.Hospital;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HospitalDaoImpl implements IhospitalDao {

    @Autowired
    SessionFactory sessionFactory;


    /**
     * for saving or updating hospital (if id is already present it will update else it will create)
     * @param hospital containing hospital entity
     * @return Hospital object
     * @see Hospital
     */
    @Override
    public Hospital hospitalSaveorUpdate(Hospital hospital) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(hospital);
        return hospital;

    }


    /**
     * for getting hospital by hospital id
     * @param hospitalId containing hospital id
     * @return Hospital object
     * @see Hospital
     */
    @Override
    public Hospital getHospital(Integer hospitalId) {

        Session session = sessionFactory.getCurrentSession();
        Hospital hospital = (Hospital) session.get(Hospital.class, hospitalId);
        return hospital;

    }


    /**
     * for getting hospitals by their city
     * @param city containing city
     * @param start needed for providing starting index in pagination
     * @param max needed for providing maximum results per request in pagination
     * @return Hospital object
     * @see Hospital
     */
    @Override
    public List<Hospital> hospitalListByCity(String city, Integer start, Integer max) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Hospital where city=:city");
        query.setParameter("city", city);
        query.setFirstResult(start);
        query.setMaxResults(max);

        if (query.list().isEmpty()) {
            return null;
        }

        return query.list();
    }


    /**
     * for getting all hospitals
     * @param start needed for providing starting index in pagination
     * @param max needed for providing maximum results per request in pagination
     * @return Hospital object
     * @see Hospital
     */
    @Override
    public List<Hospital> hospitalList(Integer start, Integer max) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Hospital");
        query.setFirstResult(start);
        query.setMaxResults(max);

        if (query.list().isEmpty()) {
            return null;
        }

        return query.list();
    }


    /**
     * for getting hospital list by hospital id
     * @param hospitalId containing hospital id
     * @param start needed for providing starting index in pagination
     * @param max needed for providing maximum results per request in pagination
     * @return Doctor list
     * @see Doctor
     */
    @Override
    public List<Doctor> doctorsListByHospitalId(Integer hospitalId, Integer start, Integer max) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select doctorSet from Hospital as h where h.id=:hospitalId");
        query.setParameter("hospitalId", hospitalId);
        query.setFirstResult(start);
        query.setMaxResults(max);

        if (query.list().isEmpty()) {
            return null;
        }
        return query.list();
    }
}

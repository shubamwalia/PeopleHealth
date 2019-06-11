package com.backend.api.model.dao.impl;

import com.backend.api.model.dao.IdoctorDao;
import com.backend.api.model.entity.Doctor;
import com.backend.api.model.entity.Hospital;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDaoImpl implements IdoctorDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Doctor doctorAdd(Doctor doctor) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(doctor);
        return doctor;

    }

    @Override
    public List<Object> doctorListBySpecialization(String specialization, Integer hospitalId, Integer start, Integer max) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("select doctor.id,doctor.mobileNumber,doctor.name,doctor.qualification,doctor.specialization from doctor inner join hospital_doctor on doctor.id=hospital_doctor.doctorSet_id inner join hospital on hospital.id=hospital_doctor.hospital_id where hospital.id=:hospitalId and doctor.specialization=:specialization");

        query.setParameter("specialization", specialization);
        query.setParameter("hospitalId", hospitalId);

        query.setFirstResult(start);
        query.setMaxResults(max);

        if (query.list().isEmpty()) {
            return null;
        }

        return query.list();
    }

    @Override
    public Doctor doctorGet(Integer doctorId) {

        Session session = sessionFactory.getCurrentSession();
        Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);

        return doctor;
    }
}

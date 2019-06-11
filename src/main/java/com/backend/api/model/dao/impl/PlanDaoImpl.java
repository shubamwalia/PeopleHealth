package com.backend.api.model.dao.impl;

import com.backend.api.model.dao.IplanDao;
import com.backend.api.model.entity.Message;
import com.backend.api.model.entity.Plan;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanDaoImpl implements IplanDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Plan> getPlansOfHospital(int start, int max) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Plan order by id desc");
        query.setFirstResult(start);
        query.setMaxResults(max);

        List<Plan> plans = query.list();

        return plans;
    }

    @Override
    public Plan getPlan(int pid) {
        Session session = sessionFactory.getCurrentSession();
        return (Plan) session.get(Plan.class, pid);
    }

    @Override
    public int payment(int amount, int pid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Plan set amountPaid=amountPaid+:amount, dueAmount=dueAmount-:amount1 where id=:id and planStatus=:planStatus");
        query.setParameter("amount", amount);
        query.setParameter("amount1", amount);
        query.setParameter("planStatus", "open");
        query.setParameter("id", pid);
        return query.executeUpdate();
    }

    @Override
    public int changeStatus(int pid) {
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery("update Plan set planStatus=:planStatus where id=:id");
        query1.setParameter("planStatus", "closed");
        query1.setParameter("id", pid);
        return query1.executeUpdate();
    }

    @Override
    public boolean checkMessageCustomerReq(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Plan set planStatus=:planStatus where id=:id and planStatus=:planStatus1");
        query.setParameter("id", id);
        query.setParameter("planStatus", "pending");
        query.setParameter("planStatus1", "open");
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean checkEmployeeNegRes(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Plan set planStatus=:planStatus where id=:id and planStatus=:planStatus1");
        query.setParameter("id", id);
        query.setParameter("planStatus", "open");
        query.setParameter("planStatus1", "pending");
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean checkEmployeePosRes(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Plan set planStatus=:planStatus where id=:id and planStatus=:planStatus1");
        query.setParameter("id", id);
        query.setParameter("planStatus", "cancelled");
        query.setParameter("planStatus1", "pending");
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public List<Message> getMessages(int pid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select messages from Plan p where p.id=:plan_id");
        query.setParameter("plan_id", pid);
        return query.list();
    }

    @Override
    public List<Plan> getPlansOfHospital(int hospitalId, int start, int max) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select planSet from Hospital h where h.id=:hospitalId");
        query.setParameter("hospitalId", hospitalId);
        query.setFirstResult(start);
        query.setMaxResults(max);

        List<Plan> plans = query.list();

        return plans;
    }

    @Override
    public int getCustomerIdByPlanId(int pid) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("select customer_id from customer_plan where planList_id=:pid");
        query.setParameter("pid", pid);
        return (Integer) query.uniqueResult();
    }


    @Override
    public List<Plan> getPlansOfCustomer(int customerId, int start, int max) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select planList from Customer c where c.id=:customerId");
        query.setParameter("customerId", customerId);
        query.setFirstResult(start);
        query.setMaxResults(max);

        List<Plan> plans = query.list();

        return plans;
    }

    @Override
    public boolean saveOrUpdatePlan(Plan plan) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(plan);
        return true;
    }

    @Override
    public void saveOrUpdateMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(message);
    }

}

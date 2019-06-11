package com.backend.api.model.dao.impl;

import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.dao.IcustomerDao;
import com.backend.api.model.entity.Customer;
import com.backend.api.model.entity.CustomerDetail;
import com.backend.api.model.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements IcustomerDao {

    /**
     * Dependency injection of SessionFactory bean
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * For adding Customer to the database
     * @param signup contains all basic infromation about customer
     * @param customer
     * @param user
     * @return Signup Object
     */
    @Override
    public Signup addCustomer(Signup signup, Customer customer, User user) {
        Session session = sessionFactory.getCurrentSession();
        int result = (int)session.save(customer);
        int result2 = (int)session.save(user);
        customer.setUser(user);
        if(result > 0 && result2 > 0) return signup;
        return null;
    }

    /**
     * Get object of Customer whose id is given in args .
     * @param customerid
     * @return Customer object
     */
    @Override
    public Customer getCustomerWithID(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = (Customer) session.get(Customer.class, id);
        return customer;
    }

    /**
     * Get Customer Details of the customer whose id is given in arguments
     * @param id
     * @return CustomerDetail object
     */
    @Override
    public CustomerDetail getCustomerDetails(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.createQuery("from CustomerDetail where customer_id =:id");
        qry.setParameter("id", id);
        return (CustomerDetail) qry.uniqueResult();

    }

    /**
     * For adding customer Details to a customer
     * @param customerDetail1
     * @return CustomerDetail
     */

    @Override
    public CustomerDetail addCustomerDetails(CustomerDetail customerDetail1) {
        Session session = sessionFactory.getCurrentSession();
        int result = (int) session.save(customerDetail1);
        if (result > 0) return customerDetail1;
        return null;
    }

    /**
     * fetch list of Customer whose start and max index are given in the arguments
     * @param start
     * @param max
     * @return list of customers
     */
    @Override
    public List<Customer> getCustomers(int start, int max) {
        Session session = sessionFactory.getCurrentSession();

        Query qry = session.createQuery("from Customer order by id ASC");
        //Query query2 = session.createQuery("select count(*) from Customer");

        qry.setFirstResult(start);
        qry.setMaxResults(max);

        List l = qry.list();
        if (l != null) return l;
        return null;
    }
}

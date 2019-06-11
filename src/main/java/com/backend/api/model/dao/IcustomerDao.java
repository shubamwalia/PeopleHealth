package com.backend.api.model.dao;

import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.entity.Customer;
import com.backend.api.model.entity.CustomerDetail;
import com.backend.api.model.entity.User;

import java.util.List;

public interface IcustomerDao {

    public Signup addCustomer(Signup signup, Customer customer, User user);

    public Customer getCustomerWithID(int id);

    public CustomerDetail getCustomerDetails(int id);

    public CustomerDetail addCustomerDetails(CustomerDetail customerDetail1);

    public List<Customer> getCustomers(int start, int max);
}

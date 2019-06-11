package com.backend.api.model.service.impl;


import com.backend.api.helper.miscellaneous.UserConstants;
import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.response.CustomerDisplayFull;
import com.backend.api.helper.response.CustomerListFetch;
import com.backend.api.helper.response.GetCustomerDetail;
import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.dao.IcustomerDao;
import com.backend.api.model.entity.Customer;
import com.backend.api.model.entity.CustomerDetail;
import com.backend.api.model.entity.User;
import com.backend.api.model.service.IcustomerService;
import com.backend.api.model.service.IuserService;
import com.backend.api.model.sro.CustomerSro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements IcustomerService {

    /**
     * Dependency injection for customerdao
     */
    @Autowired
    private IcustomerDao customerDao;

    /**
     * Dependency injection from UserService bean
     *
     */
    @Autowired
    private IuserService iuserService;

    /**
     * For adding customer
     * @param signup
     * @return BasicDataResponse object
     * @see BasicDataResponse with basic details of customer with the id given to him in database
     */
    @Transactional
    @Override
    public BasicDataResponse addCustomer(Signup signup) {
        Customer customer = new Customer(signup.getName(), signup.getCity());
        User userEncoded = iuserService.addUser(new User(signup, UserConstants.ENABLE));
        customer.setUser(userEncoded);
        Signup signupResponse = customerDao.addCustomer(signup, customer, userEncoded);

        signup.setId(customer.getId());
        BasicDataResponse basicDataResponse = new BasicDataResponse(signup);
        //System.out.println("id == " + customer.getId());
        return basicDataResponse;
    }

    /**
     * For adding customer details
     * @param getCustomerDetail
     * @param customerid
     * @return CustomerDisplayFull whose id matches to the customerid given in args
     * @see CustomerDisplayFull
     */
    @Transactional
    @Override
    public CustomerDisplayFull addCustomerDetails(GetCustomerDetail getCustomerDetail, int id) {
        Customer customer = customerDao.getCustomerWithID(id);

        CustomerDetail customerDetail = customerDao.getCustomerDetails(id);
        if (customerDetail == null) {
            CustomerDetail customerDetail1 = new CustomerDetail(customer, getCustomerDetail.getPhoneNumber(), getCustomerDetail.getDob());
            customerDetail1 = customerDao.addCustomerDetails(customerDetail1);
            System.out.println(customerDetail1 + "customer");
            CustomerDisplayFull customerDisplayFull = new CustomerDisplayFull(customer, customerDetail1);
            return customerDisplayFull;
        }
        return null;
    }

    /**
     * For fetching data of the customer whose id is given in the params
     * @param customerid
     * @return CustomerDisplayFull object
     * @see CustomerDisplayFull
     */
    @Transactional
    @Override
    public CustomerDisplayFull getCustomerById(int id) {
        Customer customer = customerDao.getCustomerWithID(id);
        System.out.println(customer + "customer");
        CustomerDetail customerDetail = customerDao.getCustomerDetails(id);
        System.out.println(customerDetail + " customerDetail");
        if(customer == null) throw new NullPointerException();
        if (customer != null && customerDetail != null) {
            CustomerSro customerSro = new CustomerSro(customer, customerDetail);
            CustomerDisplayFull customerDisplayFull = new CustomerDisplayFull(customerSro);
            System.out.println(customerDisplayFull);
            return customerDisplayFull;
        } else if (customer != null && customerDetail == null) {
            CustomerSro customerSro = new CustomerSro(customer);
            /*customerSro.setId(customer.getId());
            customerSro.setName(customer.getName());
            customerSro.setCity(customer.getCity());
            customerSro.setProfilePhotoUrl(customer.getProfilePhotoUrl());
            customerSro.setPhn_no(null);
            customerSro.setDob(null);
            */
            CustomerDisplayFull customerDisplayFull = new CustomerDisplayFull(customerSro);
            return customerDisplayFull;
        }
        return null;

    }

    /**
     * fetching customers list whose starting index and last index is given as arguments
     * @param start
     * @param max
     * @return list of CustomerListFetch type
     * @see CustomerListFetch
     */
    @Transactional
    @Override
    public List<CustomerListFetch> getCustomers(int start, int max) {
        List<Customer> customerList = customerDao.getCustomers(start, max);
        // Iterator it = customerList.iterator();
        List<CustomerListFetch> customerDisplayList = new ArrayList<CustomerListFetch>();
        for (Customer customer : customerList) {
            CustomerListFetch customerListFetch = new CustomerListFetch();
            customerListFetch.setId(customer.getId());
            customerListFetch.setName(customer.getName());
            customerListFetch.setCity(customer.getCity());
            CustomerDetail customerDetail = customerDao.getCustomerDetails(customer.getId());
            if (customerDetail != null) {
                customerListFetch.setPhoneNumber(customerDetail.getPhn_no());
                customerListFetch.setDob(customerDetail.getDob());
                customerDisplayList.add(customerListFetch);
            } else {
                customerDisplayList.add(customerListFetch);
                continue;
            }
        }
        return customerDisplayList;
    }
}

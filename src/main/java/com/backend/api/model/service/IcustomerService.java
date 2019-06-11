package com.backend.api.model.service;

import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.response.CustomerDisplayFull;
import com.backend.api.helper.response.CustomerListFetch;
import com.backend.api.helper.response.GetCustomerDetail;
import com.backend.api.helper.validation_objects.Signup;

import java.util.List;

public interface IcustomerService {

    BasicDataResponse addCustomer(Signup signup);

    CustomerDisplayFull addCustomerDetails(GetCustomerDetail getCustomerDetail, int id);

    CustomerDisplayFull getCustomerById(int id);

    List<CustomerListFetch> getCustomers(int start, int max);
}

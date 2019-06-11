package com.backend.api.controller;


import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.response.CustomerDisplayFull;
import com.backend.api.helper.response.CustomerListFetch;
import com.backend.api.helper.response.GetCustomerDetail;
import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.service.IcustomerService;
import com.backend.api.model.service.Is3Service;
import com.backend.api.model.service.impl.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CustomerController {

    /**
     * Dependency injection from S3Service bean
     */
    @Autowired
    Is3Service is3Service;

    /**
     * Dependency injection from customerService bean
     */
    @Autowired
    private IcustomerService icustomerService;

    /**
     * for adding hospital
     *
     * @param signup
     * @return object containing basic data
     */
    @PostMapping(value = "/customers", consumes = "application/json")
    public Object addCustomer(@Valid @RequestBody Signup signup) {

        BasicDataResponse basicDataResponse = icustomerService.addCustomer(signup);
        return basicDataResponse;
    }

    /**
     * for adding Customer Detail
     *
     * @param customerID
     *
     * @return object containing customer data
     */
    @PutMapping(value = "/customers/addDetails/{id}", consumes = "application/json")
    public Object addCustomerDetails(@Valid @RequestBody GetCustomerDetail getCustomerDetail, @PathVariable("id") int id) {
        CustomerDisplayFull customerDisplayFull = icustomerService.addCustomerDetails(getCustomerDetail, id);
        return customerDisplayFull;
    }


    /**
     * for getting customer List
     *
     * @param start for starting index per request
     * @param max   for maximum results per request
     * @return object containing eager customer list
     */
    @GetMapping("/customers/{start}/{max}")
    @ResponseBody
    public Object getCustomers(@PathVariable("start") int start, @PathVariable("max") int max) {

        List<CustomerListFetch> customerList = icustomerService.getCustomers(start, max);
        return customerList;
    }

    /**
     * for getting customer by customerID
     *
     * @param customerID
     * @return object containing complete data of Customer
     */
    @GetMapping(value = "/customers/{id}")
    @ResponseBody
    public Object getCustomerById(@PathVariable("id") int id) {
        CustomerDisplayFull customerDisplayFull = icustomerService.getCustomerById(id);
        return customerDisplayFull;
    }

    /**
     * for adding image of any customer
     *
     * @param customerid
     * @param multipartHttpServletRequest
     * @return location of image in s3
     */
    @PostMapping(value = "customers/upload/{id}", consumes = {"multipart/form-data"})
    public Object uploadProfile(@PathVariable("id") int id, MultipartHttpServletRequest multipartHttpServletRequest) {
        return is3Service.photoUpload(id,multipartHttpServletRequest);
    }


}

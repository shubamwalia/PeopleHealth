package com.backend.api.controller;


import com.backend.api.exceptionhandler.InvalidDateException;
import com.backend.api.exceptionhandler.InvalidDownPaymentAndInstallmentException;
import com.backend.api.exceptionhandler.InvalidPaymentException;
import com.backend.api.helper.validation_objects.Payment;
import com.backend.api.model.service.IcustomerService;
import com.backend.api.model.service.IplanService;
import com.backend.api.model.sro.MessageSro;
import com.backend.api.model.sro.PlanSro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlanController {

    /**
     * Dependency injection from PlanService bean
     */
    @Autowired
    IplanService planService;

    /**
     * Dependency injection from CustomerService bean
     */
    @Autowired
    IcustomerService customerService;

    /**
     * For Plan Preview
     * @return list objects of installments in json format
     * @param  plan which have details of plan
     */
    @PostMapping(value = "/plans/PlanPreview", consumes = "application/json")
    public Object getPlanPreview(@RequestBody @Valid PlanSro plan) throws ParseException {
        return planService.getPlanPreview(plan);
    }

    /**
     * For Plan Creation
     * @return boolean (true on success false on failure)
     * @param customerid is customer id
     * @param hospitalId is hospital id
     * @param doctorid is doctor id
     * @param  plan which have details of plan
     */
    @PostMapping(value = "/plans/createPlan/{customerid}/{doctorid}/{hospitalId}", consumes = "application/json")
    public Object addPlan(@PathVariable("customerid") int customerid, @PathVariable("doctorid") int doctorid,
                          @PathVariable("hospitalId") int hospitalId, @RequestBody @Valid PlanSro plan) throws InvalidDownPaymentAndInstallmentException, ParseException, InvalidDateException {
        boolean flag = planService.createPlan(plan, customerid, doctorid, hospitalId);
        if (flag && planService.addDoctorAndCustomer(customerid, doctorid)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * For getting all plans
     * @return list of all plans
     * @param start is starting index of pagination
     * @param max is max amount of data of pagination
     */
    @GetMapping(value = "/plans/planDetails/{start}/{max}")
    public Object getPlans(@PathVariable("start") int start, @PathVariable("max") int max) {
        return planService.getAllPlans(start, max);
    }

    /**
     * For getting plan by its id
     * @return object containing plan's detail
     * @param  pid is plan id
     */
    @GetMapping(value = "/plans/planDetail/{pid}")
    public Object getPlan(@PathVariable int pid) {
        PlanSro plan = planService.getPlan(pid);
        return plan;
    }

    /**
     * For getting plan's transactions by its id
     * @return list of object containing plan's transaction details
     * @param pid is plan id
     */
    @GetMapping(value = "/plans/planTransactions/{pid}")
    public Object getPlanTransactions(@PathVariable int pid) throws ParseException {
        return planService.getPlanTransactions(pid);
    }

    /**
     * For installment payment of plan
     * @return boolean object (true in success and false in failure and sometimes throws exception for wrong request)
     * @param payment this contains amount of payment
     * @param pid is plan id
     */
    @PutMapping(value = "/plans/planPayment/{pid}", consumes = "application/json")
    public Object payment(@PathVariable("pid") int pid, @RequestBody @Valid Payment payment) throws InvalidPaymentException {
        if (planService.payment(Integer.parseInt(payment.getAmount()), pid)) {
            return true;
        }
        return false;
    }

    /**
     * For customer making request of plan cancellation with sending reason
     * @return boolean object (true in success and false in failure)
     * @param id is plan id
     * @param message this contains message details
     */
    @PutMapping(value = "/plans/customerReq/{id}", consumes = "application/json")
    public Object customerReq(@PathVariable("id") int id, @RequestBody @Valid MessageSro message) {
        if (planService.customerReq(id, message)) {
            return true;
        }
        return false;
    }

    /**
     * For employee response on customer making request of plan cancellation ( sending reason )
     * For rejecting customers request
     * @return boolean object (true in success and false in failure)
     * @param id is plan id
     * @param message this contains message details
     */
    @PutMapping(value = "/plans/employeeNegRes/{id}", consumes = "application/json")
    public Object employeeNegRes(@PathVariable("id") int id, @RequestBody @Valid MessageSro message) {
        if (planService.employeeNegRes(id, message)) {
            return true;
        }
        return false;
    }

    /**
     * For employee response on customer making request of plan cancellation ( sending reason )
     * For accepting customers request
     * @return boolean object (true in success and false in failure)
     * @param id is plan id
     * @param message this contains message details
     */
    @PutMapping(value = "/plans/employeePosRes/{id}", consumes = "application/json")
    public Object employeePosRes(@PathVariable("id") int id, @RequestBody @Valid MessageSro message) {
        if (planService.employeePosRes(id, message)) {
            return true;
        }
        return false;
    }

    /**
     * For getting reasons(messages) related to a particular plan
     * @return List of reasons(messages)
     * @param pid is plan id
     */
    @GetMapping(value = "/plans/messages/{pid}")
    public Object getMessages(@PathVariable int pid) throws ParseException {
        return planService.getMessages(pid);
    }

    /**
     * For getting all plans of a particular hospital by its id
     * @return list of all plans f a hospital
     * @param hospitalId is hospital id
     * @param start is starting index of pagination
     * @param max is max amount of data of pagination
     */
    @GetMapping("/plans/allhospitalplans/{hospitalId}/{start}/{max}")
    public Object getPlansOfHospital(@PathVariable("hospitalId") int hospitalId, @PathVariable("start") int start, @PathVariable("max") int max) {
        return planService.getAllPlans(hospitalId, start, max);
    }

    /**
     * For getting all plans of a particular customer by its id
     * @return list of all plans f a customer
     * @param customerId is customer id
     * @param start is starting index of pagination
     * @param max is max amount of data of pagination
     */
    @GetMapping("/plans/allcustomerplans/{customerId}/{start}/{max}")
    public Object getPlansOfCustomer(@PathVariable("customerId") int customerId, @PathVariable("start") int start, @PathVariable("max") int max) {
        return planService.getPlansOfCustomer(customerId, start, max);
    }

}

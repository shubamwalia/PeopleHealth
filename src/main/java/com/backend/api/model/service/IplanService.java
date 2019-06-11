package com.backend.api.model.service;

import com.backend.api.exceptionhandler.InvalidDateException;
import com.backend.api.exceptionhandler.InvalidDownPaymentAndInstallmentException;
import com.backend.api.exceptionhandler.InvalidPaymentException;
import com.backend.api.helper.response.PlanPreview;
import com.backend.api.model.sro.MessageSro;
import com.backend.api.model.sro.PlanSro;

import java.text.ParseException;
import java.util.List;

public interface IplanService {
    List<PlanPreview> getPlanPreview(PlanSro plan) throws ParseException;

    List<PlanSro> getAllPlans(int start, int max);

    PlanSro getPlan(int pid);

    List getPlanTransactions(int pid) throws ParseException;

    int checkTransactions(int pid);

    boolean payment(int amount, int pid) throws InvalidPaymentException;

    boolean customerReq(int id, MessageSro message);

    boolean employeeNegRes(int id, MessageSro message);

    boolean employeePosRes(int id, MessageSro message);

    List<MessageSro> getMessages(int pid);

    List<PlanSro> getAllPlans(int hospitalId, int start, int max);

    boolean createPlan(PlanSro plan, int customerid, int doctorid, int hospitalId) throws InvalidDownPaymentAndInstallmentException, ParseException, InvalidDateException;

    boolean addDoctorAndCustomer(int customerid, int doctorid);

    List<PlanSro> getPlansOfCustomer(int customerId, int start, int max);
}

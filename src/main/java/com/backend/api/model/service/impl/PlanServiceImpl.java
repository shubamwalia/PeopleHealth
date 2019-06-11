package com.backend.api.model.service.impl;

import com.backend.api.exceptionhandler.*;
import com.backend.api.helper.response.PlanPreview;
import com.backend.api.helper.response.PlanTransaction;
import com.backend.api.model.dao.IcustomerDao;
import com.backend.api.model.dao.IdoctorDao;
import com.backend.api.model.dao.IhospitalDao;
import com.backend.api.model.dao.IplanDao;
import com.backend.api.model.entity.*;
import com.backend.api.model.service.IplanService;
import com.backend.api.model.sro.MessageSro;
import com.backend.api.model.sro.PlanSro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PlanServiceImpl implements IplanService {

    /**
     * Dependency injection from Plan Dao bean
     */
    @Autowired
    private IplanDao planDao;

    /**
     * Dependency injection from Customer Dao bean
     */
    @Autowired
    private IcustomerDao customerDao;

    /**
     * Dependency injection from hospital Dao bean
     */
    @Autowired
    private IhospitalDao hospitalDao;

    /**
     * Dependency injection from doctor Dao bean
     */
    @Autowired
    private IdoctorDao doctorDao;

    /**
     * For Plan Preview
     * @return list objects of installments in json format
     * @param  plan which have details of plan
     * @throws ParseException this for converting string date to date object
     */
    @Override
    public List<PlanPreview> getPlanPreview(PlanSro plan) throws ParseException {
        List<PlanPreview> list = new ArrayList<PlanPreview>();

        int a = plan.getPlanAmount() - plan.getDownPayment();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");

        Date currentDate;

        if (plan.getStartDate() != null) {
            currentDate = dateFormat.parse(plan.getStartDate());
        } else {
            currentDate = new Date();
        }

        Calendar c = Calendar.getInstance();

        c.setTime(currentDate);
        list.add(new PlanPreview(0, plan.getDownPayment(), plan.getPlanAmount() - plan.getDownPayment(), dateFormat.format(currentDate)));

        c.add(Calendar.DAY_OF_MONTH, plan.getPlanFrequency());
        currentDate = c.getTime();

        for (int i = 0; a > 0; i++) {

            c.setTime(currentDate);

            if (a >= plan.getInstallmentAmount()) {
                a = a - plan.getInstallmentAmount();
                list.add(new PlanPreview(i + 1, plan.getInstallmentAmount(), a, dateFormat.format(currentDate)));
            } else {
                list.add(new PlanPreview(i + 1, a, 0, dateFormat.format(currentDate)));
                a = a - plan.getInstallmentAmount();
            }

            c.add(Calendar.DAY_OF_MONTH, plan.getPlanFrequency());
            currentDate = c.getTime();

        }

        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * For getting all plans
     * @return list of all plans
     * @param start is starting index of pagination
     * @param max is max amount of data of pagination
     */
    @Override
    @Transactional
    public List<PlanSro> getAllPlans(int start, int max) {

        List<Plan> plans = planDao.getPlansOfHospital(start, max);

        if (!plans.isEmpty()) {
            List<PlanSro> planList = new ArrayList<>();

            for (Plan plan : plans) {
                planList.add(new PlanSro(plan));
            }

            return planList;
        }

        return null;

    }

    /**
     * For getting plan by its id
     * @return object containing plan's detail
     * @param  pid is plan id
     */
    @Override
    @Transactional
    public PlanSro getPlan(int pid) {
        return new PlanSro(planDao.getPlan(pid));
    }

    /**
     * For getting plan's transactions by its id
     * @return list of object containing plan's transaction details
     * @param pid is plan id
     * @throws ParseException this for converting string date to date object
     */
    @Override
    @Transactional
    public List<PlanTransaction> getPlanTransactions(int pid) throws ParseException {
        PlanSro plan = getPlan(pid);

        List<PlanTransaction> list = new ArrayList<PlanTransaction>();

        int amountCheck1 = plan.getPlanAmount() - plan.getDownPayment();

        int amountCheck2 = plan.getPlanAmount();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Date currentDate = new Date();

        Date currentDate = dateFormat.parse(plan.getStartDate());

        Calendar calendar = Calendar.getInstance();

        int amountPaid = plan.getAmountPaid() - plan.getDownPayment();

        int amountPaidCheck = plan.getAmountPaid();

        calendar.setTime(currentDate);
        list.add(new PlanTransaction(0, plan.getDownPayment(), dateFormat.format(currentDate), true));

        calendar.add(Calendar.DAY_OF_MONTH, plan.getPlanFrequency());
        currentDate = calendar.getTime();

        for (int i = 0; amountCheck1 > 0; i++) {

            calendar.setTime(currentDate);

            if (amountPaid > 0) {

                if (amountPaid >= plan.getInstallmentAmount()) {

                    list.add(new PlanTransaction(i + 1, plan.getInstallmentAmount(), dateFormat.format(currentDate), true));

                } else {

                    if (amountCheck2 == amountPaidCheck) {

                        list.add(new PlanTransaction(i + 1, amountPaid, dateFormat.format(currentDate), true));

                    } else {

                        list.add(new PlanTransaction(i + 1, plan.getInstallmentAmount() - amountPaid, dateFormat.format(currentDate), false));
                    }
                }


                amountPaid = amountPaid - plan.getInstallmentAmount();

            } else {
                if (amountCheck1 >= plan.getInstallmentAmount()) {
                    list.add(new PlanTransaction(i + 1, plan.getInstallmentAmount(), dateFormat.format(currentDate), false));
                } else {
                    list.add(new PlanTransaction(i + 1, amountCheck1, dateFormat.format(currentDate), false));
                }
            }

            amountCheck1 = amountCheck1 - plan.getInstallmentAmount();

            calendar.add(Calendar.DAY_OF_MONTH, plan.getPlanFrequency());
            currentDate = calendar.getTime();

        }

        return list;
    }

    @Override
    @Transactional
    public int checkTransactions(int pid) {

        PlanSro plan = getPlan(pid);

        int a = plan.getPlanAmount() - plan.getDownPayment();

        int k = 0;

        int amountPaid = plan.getAmountPaid() - plan.getDownPayment();

        for (int i = 0; a > 0; i++) {

            if (amountPaid > 0) {

                if (amountPaid >= plan.getInstallmentAmount()) {
                    k = plan.getInstallmentAmount();
                } else {
                    return plan.getInstallmentAmount() - amountPaid;
                }

                amountPaid = amountPaid - plan.getInstallmentAmount();

            } else {

                if (a >= plan.getInstallmentAmount()) {
                    return plan.getInstallmentAmount();
                } else {
                    return a;
                }

            }

            a = a - plan.getInstallmentAmount();

        }

        System.out.println(k);
        return k;
    }

    /**
     * For installment payment of plan
     * @return boolean object (true in success and false in failure and sometimes throws exception for wrong request)
     * @param amount is amount of payment
     * @param pid is plan id
     * @throws InvalidPaymentException this is custom made exception for invalid payment amount
     */
    @Override
    @Transactional
    public boolean payment(int amount, int pid) throws InvalidPaymentException {
        PlanSro plan = getPlan(pid);

        if (amount >= checkTransactions(pid) && plan.getPlanAmount() >= (amount + plan.getAmountPaid())) {
            int result = planDao.payment(amount, pid);
            if (plan.getAmountPaid() + amount == plan.getPlanAmount()) {
                int result1 = planDao.changeStatus(pid);
                if (result1 > 0) {

                    int customerId = planDao.getCustomerIdByPlanId(pid);
                    Customer customer = customerDao.getCustomerWithID(customerId);
                    customer.setOpenPlan(customer.getOpenPlan() - 1);
                    customer.setClosedPlan(customer.getClosedPlan() + 1);

                }
            }
            return result > 0;
        }
        else {
            throw new InvalidPaymentException("payment cant be greater than due Amount and cant be less than current installment amount.");
        }
    }

    /**
     * For customer making request of plan cancellation with sending reason
     * @return boolean object (true in success and false in failure)
     * @param id is plan id
     * @param message this contains message details
     */
    @Override
    @Transactional
    public boolean customerReq(int id, MessageSro message) {

        if (message.getDateAndTime() == null) {

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
            message.setDateAndTime(dateFormat.format(new Date()));

        }

        Message message1 = new Message(message);

        if (planDao.checkMessageCustomerReq(id)) {

            planDao.saveOrUpdateMessage(message1);

            Plan plan = planDao.getPlan(id);
            plan.addMessageUtil(message1);
            planDao.saveOrUpdatePlan(plan);

            int customerId = planDao.getCustomerIdByPlanId(id);
            Customer customer = customerDao.getCustomerWithID(customerId);
            customer.setOpenPlan(customer.getOpenPlan() - 1);
            customer.setPendingPlan(customer.getPendingPlan() + 1);

            return true;
        } else
            return false;
    }

    /**
     * For employee response on customer making request of plan cancellation ( sending reason )
     * For rejecting customers request
     * @return boolean object (true in success and false in failure)
     * @param id is plan id
     * @param message this contains message details
     */
    @Override
    @Transactional
    public boolean employeeNegRes(int id, MessageSro message) {
        if (message.getDateAndTime() == null) {

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
            message.setDateAndTime(dateFormat.format(new Date()));

        }
        Message message1 = new Message(message);
        if (planDao.checkEmployeeNegRes(id)) {

            planDao.saveOrUpdateMessage(message1);

            Plan plan = planDao.getPlan(id);
            plan.addMessageUtil(message1);
            planDao.saveOrUpdatePlan(plan);

            int customerId = planDao.getCustomerIdByPlanId(id);
            Customer customer = customerDao.getCustomerWithID(customerId);
            customer.setOpenPlan(customer.getOpenPlan() + 1);
            customer.setPendingPlan(customer.getPendingPlan() - 1);

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
    @Override
    @Transactional
    public boolean employeePosRes(int id, MessageSro message) {
        if (message.getDateAndTime() == null) {

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
            message.setDateAndTime(dateFormat.format(new Date()));

        }
        Message message1 = new Message(message);
        if (planDao.checkEmployeePosRes(id)) {

            planDao.saveOrUpdateMessage(message1);

            Plan plan = planDao.getPlan(id);
            plan.addMessageUtil(message1);
            planDao.saveOrUpdatePlan(plan);

            int customerId = planDao.getCustomerIdByPlanId(id);
            Customer customer = customerDao.getCustomerWithID(customerId);
            customer.setCancelledPlan(customer.getCancelledPlan() + 1);
            customer.setPendingPlan(customer.getPendingPlan() - 1);

            return true;
        } else
            return false;
    }

    /**
     * For getting reasons(messages) related to a particular plan
     * @return List of reasons(messages)
     * @param pid is plan id
     */
    @Override
    @Transactional
    public List<MessageSro> getMessages(int pid) {

        List<Message> messages = planDao.getMessages(pid);

        if (!messages.isEmpty()) {
            List<MessageSro> messageList = new ArrayList<>();

            for (Message message : messages) {
                messageList.add(new MessageSro(message));
            }
            return messageList;

        } else {

            return null;
        }
    }

    /**
     * For getting all plans of a particular hospital by its id
     * @return list of all plans f a hospital
     * @param hospitalId is hospital id
     * @param start is starting index of pagination
     * @param max is max amount of data of pagination
     */
    @Override
    @Transactional
    public List<PlanSro> getAllPlans(int hospitalId, int start, int max) {
        List<Plan> plans = planDao.getPlansOfHospital(hospitalId, start, max);

        if (!plans.isEmpty()) {
            List<PlanSro> planList = new ArrayList<>();

            for (Plan plan : plans) {
                planList.add(new PlanSro(plan));
            }

            return planList;
        }

        return null;
    }

    /**
     * For Plan Creation
     * @return boolean (true on success false on failure)
     * @param customerid is customer id
     * @param hospitalId is hospital id
     * @param doctorid is doctor id
     * @param  plan which have details of plan
     */
    @Override
    @Transactional
    public boolean createPlan(PlanSro plan, int customerid, int doctorid, int hospitalId) throws InvalidDownPaymentAndInstallmentException, ParseException, InvalidDateException {

        if (plan.getDownPayment() > plan.getPlanAmount() || plan.getInstallmentAmount() > plan.getPlanAmount() || ((plan.getInstallmentAmount() > plan.getPlanAmount() - plan.getDownPayment()) && (plan.getPlanAmount() != plan.getDownPayment()))) {
            throw new InvalidDownPaymentAndInstallmentException("Downpayment or installment amount can't be greater than Plan Amount.");
        }


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");

        if (plan.getStartDate() == null) {

            plan.setStartDate(dateFormat.format(new Date()));

        }

        Date startDate = dateFormat.parse(plan.getStartDate());

        if(startDate.before(new Date())) {

            throw new InvalidDateException();

        }


        Plan plan1 = new Plan(plan);

        Customer customer = customerDao.getCustomerWithID(customerid);
        if (customer == null){
            throw new NoDataFoundException("customer data not found");
        }
        Hospital hospital = hospitalDao.getHospital(hospitalId);
        if (hospital == null){
            throw new NoDataFoundException("hospital data not found");
        }
        Doctor doctor = doctorDao.doctorGet(doctorid);
        if (doctor == null){
            throw new NoDataFoundException("doctor data not found");
        }

        plan1.setDoctor(doctor);

        if (plan.getDueAmount() == 0) {
            plan.setPlanStatus("closed");
            customer.setClosedPlan(customer.getClosedPlan() + 1);
            customer.setOpenPlan(customer.getOpenPlan() - 1);
        }

        if (planDao.saveOrUpdatePlan(plan1)) {

            customer.addPlans(plan1);

            plan1.setDoctor(doctor);

            hospital.addPlanUtil(plan1);

            customer.setOpenPlan(customer.getOpenPlan() + 1);
            customer.setTotalPlan(customer.getTotalPlan() + 1);

            planDao.saveOrUpdatePlan(plan1);

            return true;
        }

        throw new PlanNotCreated("plan not created");

    }

    /**
     * For Plan Creation
     * @return boolean (true on success false on failure)
     * @param customerid is customer id
     * @param doctorid is doctor id
     */
    @Override
    @Transactional
    public boolean addDoctorAndCustomer(int customerid, int doctorid) {

        Customer customer = customerDao.getCustomerWithID(customerid);
        if (customer == null){
            throw new NoDataFoundException("customer data not found");
        }

        Doctor doctor = doctorDao.doctorGet(doctorid);
        if (doctor == null){
            throw new NoDataFoundException("doctor data not found");
        }

        doctor.addCustomertil(customer);

        return true;
    }

    /**
     * For getting all plans of a particular customer by its id
     * @return list of all plans f a customer
     * @param customerId is customer id
     * @param start is starting index of pagination
     * @param max is max amount of data of pagination
     */
    @Override
    @Transactional
    public List<PlanSro> getPlansOfCustomer(int customerId, int start, int max) {
        List<Plan> plans = planDao.getPlansOfCustomer(customerId, start, max);

        if (!plans.isEmpty()) {
            List<PlanSro> planList = new ArrayList<>();

            for (Plan plan : plans) {
                planList.add(new PlanSro(plan));
            }

            return planList;
        }

        return null ;
    }

}
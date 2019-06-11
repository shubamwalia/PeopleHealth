package com.backend.api.model.dao;

import com.backend.api.model.entity.Message;
import com.backend.api.model.entity.Plan;

import java.util.List;

public interface IplanDao {
    List<Plan> getPlansOfHospital(int start, int max);

    Plan getPlan(int pid);

    int payment(int amount, int pid);

    int changeStatus(int pid);

    boolean checkMessageCustomerReq(int id);

    boolean checkEmployeeNegRes(int id);

    boolean checkEmployeePosRes(int id);

    List<Message> getMessages(int pid);

    List<Plan> getPlansOfHospital(int hospitalId, int start, int max);

    int getCustomerIdByPlanId(int pid);

    List<Plan> getPlansOfCustomer(int customerId, int start, int max);

    boolean saveOrUpdatePlan(Plan plan);

    void saveOrUpdateMessage(Message message1);
}

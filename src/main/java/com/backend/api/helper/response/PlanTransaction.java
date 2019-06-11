package com.backend.api.helper.response;

public class PlanTransaction {

    private int installmentno;
    private int installmentAmount;
    private String dueDate;
    private boolean installmentPaid;

    public PlanTransaction() {
    }

    public PlanTransaction(int installmentno, int amount, String date, boolean paid) {
        this.installmentno = installmentno;
        this.installmentAmount = amount;
        this.dueDate = date;
        this.installmentPaid = paid;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getInstallmentno() {
        return installmentno;
    }

    public void setInstallmentno(int installmentno) {
        this.installmentno = installmentno;
    }

    public int getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(int installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public boolean getInstallmentPaid() {
        return installmentPaid;
    }

    public void setInstallmentPaid(boolean installmentPaid) {
        this.installmentPaid = installmentPaid;
    }
}

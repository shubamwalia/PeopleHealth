package com.backend.api.helper.response;

public class PlanPreview {

    private int installmentno;
    private int amount;
    private int remainingamount;
    private String startDate;

    public PlanPreview() {
    }

    public PlanPreview(int installmentno, int amount, int remainingamount, String startDate) {
        this.installmentno = installmentno;
        this.amount = amount;
        this.remainingamount = remainingamount;
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getRemainingamount() {
        return remainingamount;
    }

    public void setRemainingamount(int remainingamount) {
        this.remainingamount = remainingamount;
    }

    public int getInstallmentno() {
        return installmentno;
    }

    public void setInstallmentno(int installmentno) {
        this.installmentno = installmentno;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

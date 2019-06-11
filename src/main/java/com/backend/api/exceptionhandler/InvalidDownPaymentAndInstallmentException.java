package com.backend.api.exceptionhandler;

public class InvalidDownPaymentAndInstallmentException extends Exception {

    public InvalidDownPaymentAndInstallmentException(String s) {
        super(s);

    }
    public String getField() {
        return "downpayment amount and installment";
    }
}

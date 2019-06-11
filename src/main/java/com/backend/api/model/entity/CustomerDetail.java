package com.backend.api.model.entity;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "customer_detail")
public class CustomerDetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int cid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @Column(name = "phn_no")
    private String phn_no;

    @NotNull
    @Column(name = "dob")
    private String dob;

    public CustomerDetail() {
    }

    public CustomerDetail(Customer customer, CustomerDetail customerDetail) {
        this.customer = customer;
        this.phn_no = customerDetail.phn_no;
        this.dob = customerDetail.dob;
    }

    public CustomerDetail(Customer customer, String phn_no, String dob) {
        this.customer = customer;
        this.phn_no = phn_no;
        this.dob = dob;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPhn_no() {
        return phn_no;
    }

    public void setPhn_no(String phn_no) {
        this.phn_no = phn_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


}

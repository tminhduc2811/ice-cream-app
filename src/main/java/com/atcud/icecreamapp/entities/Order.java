package com.atcud.icecreamapp.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "recipe_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(
            cascade = {CascadeType.REMOVE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "order",
            cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH})

    private List<OrderDetail> orderDetails;

    @Column(name = "payment_option")
    private String paymentOption;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    private String status;

    @Column(name = "delivery_detail")
    private String deliveryDetail;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Feedback feedback;

    public Order() {

    }

    public Order(Long id, String paymentOption, Timestamp createdDate, String notes, String status, String deliveryDetail) {
        super();
        this.id = id;
        this.paymentOption = paymentOption;
        this.createdDate = createdDate;
        this.notes = notes;
        this.status = status;
        this.deliveryDetail = deliveryDetail;
    }

    public String getDeliveryDetail() {
        return deliveryDetail;
    }

    public void setDeliveryDetail(String deliveryDetail) {
        this.deliveryDetail = deliveryDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}

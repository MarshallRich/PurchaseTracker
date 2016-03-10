package com.example;

import javax.persistence.*;

/**
 * Created by MacLap on 3/9/16.
 */

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String creditCard;

    @Column(nullable = false)
    String cVV;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;

    public Purchase() {
    }

    public Purchase(String date, String creditCard, String cVV, String category) {
        this.date = date;
        this.creditCard = creditCard;
        this.cVV = cVV;
        this.category = category;
    }
}

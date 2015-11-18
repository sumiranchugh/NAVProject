package com.atlas.dao.entities;

import javax.persistence.*;

/**
 * Created by schug2 on 11/18/2015.
 */
@Entity
@Table(name = "HOLDING")
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String name;

    private double currentPrice;

    private double lastPrice;

    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentSharePrice() {
        return currentPrice;
    }

    public void setCurrentSharePrice(double currentSharePrice) {
        this.currentPrice = currentSharePrice;
    }

    public double getLastSharePrice() {
        return lastPrice;
    }

    public void setLastSharePrice(double lastSharePrice) {
        this.lastPrice = lastSharePrice;
    }
}

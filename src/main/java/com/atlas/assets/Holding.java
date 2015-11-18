package com.atlas.assets;

import com.atlas.events.Event;
import com.atlas.events.PriceChangeEvent;
import com.atlas.pricemanager.ChangeEventListner;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by schug2 on 11/16/2015.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Holding implements ChangeEventListner<PriceChangeEvent>,Serializable {

    private String name;
    private long id;

    private double sharePrice;
    private double oldPrice;
    private double countShares;



    private double costPrice;



    public Holding() {
    }



    public Holding(String name, double sharePrice, double oldPrice) {
        this.name = name;
        this.sharePrice = sharePrice;
        this.oldPrice = oldPrice;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void setCountShares(double countShares) {
        this.countShares = countShares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSharePrice() {
        return this.sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public double getCountShares() {
        return countShares;
    }

    public void setCountShares(int countShares) {
        this.countShares = countShares;
    }

    public Double getWorth() {
        return countShares * sharePrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }


    @Override
    public String toString() {
        return "Holding{" +
                "name='" + name + '\'' +
                ", sharePrice=" + sharePrice +
                ", oldPrice=" + oldPrice +
                ", countShares=" + countShares +
                ", costPrice=" + costPrice +
                '}';
    }

    @Override
    public void processChange(PriceChangeEvent event) {
        if (event.getSource().equals(name)){
            System.out.println("event rcvd at holding");
            this.oldPrice = event.getOldPrice();
            this.sharePrice = event.getNewPrice();
            System.out.println(this);
        }
    }
}

package com.atlas.events;

import com.atlas.portfolios.PortfolioManager;
import com.atlas.pricemanager.ChangeEventListner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by schug2 on 11/16/2015.
 */

public class PriceChangeEvent implements Event, Serializable {

    private String source;

    private String destination;

    private double oldPrice;
    private double newPrice;
/*
    @Autowired
    PortfolioManager portfolioManager;*/



    public PriceChangeEvent() {
    }

    public PriceChangeEvent(String source, String destination, double oldPrice, double newPrice) {
        this.source = source;
        this.destination = destination;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }



    @Override
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public EventType getType() {
        return EventType.PRICECHANGE;
    }


}

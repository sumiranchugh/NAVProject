package com.atlas.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by schug2 on 11/18/2015.
 */
@Entity
public class PHM {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private long portfolioId;

    private long holdingId;

    private double stocksPurchased;

    private double costOfPurchase;

    public long getId() {
        return Id;
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public long getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(long holdingId) {
        this.holdingId = holdingId;
    }

    public double getStocksPurchased() {
        return stocksPurchased;
    }

    public void setStocksPurchased(double stocksPurchased) {
        this.stocksPurchased = stocksPurchased;
    }

    public double getCostOfPurchase() {
        return costOfPurchase;
    }

    public void setCostOfPurchase(double costOfPurchase) {
        this.costOfPurchase = costOfPurchase;
    }
}

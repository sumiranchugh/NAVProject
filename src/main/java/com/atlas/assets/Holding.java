package com.atlas.assets;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by schug2 on 11/16/2015.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Holding {

    private String name;

    private double sharePrice;
    private double oldPrice;
    private int countShares;

    public Holding() {
    }

    public Holding(String name, double sharePrice, double oldPrice, int countShares) {
        this.name = name;
        this.sharePrice = sharePrice;
        this.oldPrice = oldPrice;
        this.countShares = countShares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public int getCountShares() {
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
}

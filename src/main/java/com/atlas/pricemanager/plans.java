package com.atlas.pricemanager;

import com.atlas.portfolios.Portfolio;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by schug2 on 11/16/2015.
 */
@Component
public class plans {

    int planId;

    float rate;

    Date validFrom;

    Date validTo;

    Portfolio portfolio;

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}

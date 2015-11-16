package com.atlas.investors;

import com.atlas.pricemanager.plans;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by schug2 on 11/16/2015.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Investor {

    private double amountInvested;

    private Date startDate;

    private Date endDate;

    private plans plan;

    private double fundUnits;

    public double getUnitsSold() {
        return fundUnits;
    }

    public void setUnitsSold(double unitsSold) {
        this.fundUnits = unitsSold;
    }

    public Investor() {
    }

    public Investor(double amountInvested, Date startDate, Date endDate, plans planId) {
        this.amountInvested = amountInvested;
        this.startDate = startDate;
        this.endDate = endDate;
        this.plan = planId;
    }

    public double getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(double amountInvested) {
        this.amountInvested = amountInvested;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public plans getPlanId() {
        return plan;
    }

    public void setPlanId(plans planId) {
        this.plan = planId;
    }
}

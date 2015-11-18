package com.atlas.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by schug2 on 11/18/2015.
 */
@Entity
public class PIM {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private long investorId;

    private long portfolioId;

    private double amountInvested;

    private double unitsBought;

    public long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(long investorId) {
        this.investorId = investorId;
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public double getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(double amountInvested) {
        this.amountInvested = amountInvested;
    }

    public double getUnitsBought() {
        return unitsBought;
    }

    public void setUnitsBought(double unitsBought) {
        this.unitsBought = unitsBought;
    }
}

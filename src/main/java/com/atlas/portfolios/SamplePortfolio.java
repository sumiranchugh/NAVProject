package com.atlas.portfolios;

import com.atlas.assets.Holding;
import com.atlas.investors.Investor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schug2 on 11/16/2015.
 */
@Component
public class SamplePortfolio implements Portfolio {

    List<Holding> holdings = new ArrayList<>();
    List<Investor> investors = new ArrayList<>();

    private String name;



    private double baseNav ;


    private double unitsSoldToInvestors ;

    public SamplePortfolio() {

    }

    public SamplePortfolio(List<Holding> holdings, List<Investor> investors, String name, double totalUnits) {
        this.holdings = holdings;
        this.investors = investors;
        this.name = name;
        this.baseNav = totalUnits;
    }

    @Override
    public Double getBaseNav() {
        double base=0;
        for (Holding holding : holdings) {
            base+=holding.getSharePrice();
        }
        baseNav=base/holdings.size()+2;
        return baseNav;
    }

    public void setBaseNav(double baseNav) {
        this.baseNav = baseNav;
    }

    @Override
    public double getUnitsSoldToInvestors() {
        return unitsSoldToInvestors;
    }

    public void setUnitsSoldToInvestors(double unitsSoldToInvestors) {
        this.unitsSoldToInvestors = unitsSoldToInvestors;
    }

    @Override
    public List<Holding> getHoldings() {
        return new ArrayList<Holding>(holdings);
    }


    @Override
    public List<Investor> getInvestors() {
        return new ArrayList<Investor>(investors);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addInvestor(Investor investor) {

        //calculation logic to increase count of shares for each holding based on amount invested
        double units =investor.getAmountInvested()/ getNAV();
        investor.setUnitsSold(units);
        unitsSoldToInvestors += units;
        investors.add(investor);

    }

    @Override
    public void addHolding(Holding holding) {
        holdings.add(holding);
    }

    @Override
    public double getLiabilities() {
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
         stringBuffer.append("SamplePortfolio :" +name +"   units sold total:" + unitsSoldToInvestors+" Base Nav "+ baseNav);
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append("Holdings| Total Shr| Current Price     | Old Price     | Price Change      |Worth          ");
        stringBuffer.append(System.lineSeparator());
        holdings.forEach((p)->{
            stringBuffer.append(p.getName()+"      |"+ p.getCountShares() +"     |"+p.getSharePrice()+"      |"+p.getOldPrice()+"          |"+ (p.getSharePrice()-p.getOldPrice())+"     |"+p.getWorth());
       stringBuffer.append(System.lineSeparator());
        });
        stringBuffer.append("Net NAV :"+ getNAV());
        return stringBuffer.toString();
    }
}

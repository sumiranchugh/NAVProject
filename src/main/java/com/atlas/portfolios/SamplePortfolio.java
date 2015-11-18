package com.atlas.portfolios;

import com.atlas.assets.Holding;
import com.atlas.investors.Investor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by schug2 on 11/16/2015.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SamplePortfolio implements Portfolio {

    Set<Holding> holdings = new HashSet<>();
    Set<Investor> investors = new HashSet<>();

    private String name;
    private long Id;



    private double baseNav ;


    private double unitsSoldToInvestors ;
    private double liability;

    public SamplePortfolio() {

    }

    public SamplePortfolio(Set<Holding> holdings, Set<Investor> investors, String name, double totalUnits) {
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



    public void setUnitsSoldToInvestors(double unitsSoldToInvestors) {
        this.unitsSoldToInvestors = unitsSoldToInvestors;
    }

    @Override
    public Set<Holding> getHoldings() {
        return new HashSet<Holding>(holdings);
    }


    @Override
    public Set<Investor> getInvestors() {
        return new HashSet<>(investors);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addInvestor(Investor investor) {

        //calculation logic to increase count of shares for each holding based on amount invested

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
    public void setId(long id) {
        this.Id=id;
    }

    @Override
    public long getId() {
        return Id;
    }

   /* @Override
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
    }*/

    @Override
    public String toString() {
        return "SamplePortfolio{" +
                "holdings=" + holdings +
                ", investors=" + investors +
                ", name='" + name + '\'' +
                ", baseNav=" + baseNav +
                ", unitsSoldToInvestors=" + getUnitsSoldToInvestors() +
                ", NAV="+ getNAV()+
                ",cost="+ getCost()+
                ",assets"+getAssetValue()+
                '}';
    }






    public void setLiability(double liability) {
        this.liability = liability;
    }
}

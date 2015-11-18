package com.atlas.portfolios;

import com.atlas.assets.Holding;
import com.atlas.investors.Investor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by schug2 on 11/16/2015.
 */
public interface Portfolio extends Serializable{

    Set<Holding> getHoldings();

    Set<Investor> getInvestors();

    String getName();

    default double getUnitsSoldToInvestors(){
      return   getInvestors().stream().mapToDouble((p)->p.getUnitsSold()).sum();
    }

    void addInvestor(Investor investor);

    void addHolding(Holding holding);

    double getLiabilities();

    default Double getNAV() {

        if(getUnitsSoldToInvestors()==0)
            return getBaseNav();
        double assetsWorth = getHoldings().stream().mapToDouble((p) -> p.getWorth()).sum();
        double liabilities = getLiabilities();

        return (assetsWorth - liabilities) / getUnitsSoldToInvestors();

    }

    default  double getPurchasePrice(){

        return getHoldings().stream().mapToDouble((p)->p.getCostPrice()).sum();
    }

    Double getBaseNav();


    void setName(String s);

    void setId(long id);

     long getId();


}

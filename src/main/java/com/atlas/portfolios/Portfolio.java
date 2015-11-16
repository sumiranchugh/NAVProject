package com.atlas.portfolios;

import com.atlas.assets.Holding;
import com.atlas.investors.Investor;

import java.util.List;

/**
 * Created by schug2 on 11/16/2015.
 */
public interface Portfolio {

    List<Holding> getHoldings();

    List<Investor> getInvestors();

    String getName();

    double getUnitsSoldToInvestors();

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

    Double getBaseNav();


    void setName(String s);
}

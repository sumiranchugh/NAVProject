package com.atlas.dao;

import com.atlas.assets.Holding;
import com.atlas.investors.Investor;
import com.atlas.portfolios.Portfolio;

/**
 * Created by schug2 on 11/17/2015.
 */
public interface PortflioDAO {

    void createPortfoio(Portfolio portfolio);

    void addHolding(Portfolio portfolio, Holding holding);

    void updateNAV(Portfolio portfolio);

    Portfolio getPortfolio(Portfolio portfolio);

    Investor addInvestor(Portfolio portfolio, Investor investor);

    Holding createHolding(String holdingName, double currentPrice);
}

package com.atlas.portfolios;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by schug2 on 11/16/2015.
 */

@Component
public class PortfolioManager {

    Map<String, Portfolio> portfolioMap = new ConcurrentHashMap<>();

    public Portfolio getPortfolio(String name) {
        return portfolioMap.get(name);
    }

    public Collection<Portfolio> getAllPortfolios() {
        return portfolioMap.values();
    }

    public void addPortfolio(Portfolio portfolio) {
        portfolioMap.put(portfolio.getName(), portfolio);
    }

    public void removePortfolio(String name) {
        portfolioMap.remove(name);
    }
}

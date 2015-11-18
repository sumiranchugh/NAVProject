package com.atlas.portfolios;

import com.atlas.assets.Holding;
import com.atlas.dao.PortfolioDaoImpl;
import com.atlas.events.eventpublisher.PricePublisher;
import com.atlas.investors.Investor;
import com.atlas.portfolios.report.Report;
import com.atlas.portfolios.report.SummaryReport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by schug2 on 11/16/2015.
 */

@Component
public class PortfolioManager implements  ApplicationContextAware {

   private Map<String, Portfolio> portfolioMap = new ConcurrentHashMap<>();

    @Autowired
    Report report;

    @Autowired
    PortfolioDaoImpl portfolioDao;

    @Autowired
    private PricePublisher pricePublisher;

    private ApplicationContext context;

    public Double getPortfolioNAV(String name) {
        Portfolio p =portfolioMap.get(name);
        if( p!=null)
         return    p.getNAV();
        return 0d ;
    }

    public void getPortfolioSummary(String name){

        report.print(portfolioMap.get(name));
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
    public Portfolio createPortfolio(String name, double baseNav, double liability) {
      SamplePortfolio portfolio= context.getBean(SamplePortfolio.class);
        portfolio.setName(name);
        portfolio.setBaseNav(baseNav);
        portfolio.setLiability(liability);
        portfolioDao.createPortfoio(portfolio);
        portfolioMap.put(name,portfolio);
        return portfolio;
    }

    public Holding createHolding(String holdingName,double currentPrice){
     Holding h=    portfolioDao.createHolding(holdingName, currentPrice);
        pricePublisher.attach(h);
        return h;
    }



    public Holding addNewHolding(String portfolioName,String holdingName,double currentPrice, double stocksPurchased, double costPrice){
       Portfolio portfolio= portfolioMap.get(portfolioName);
       Holding h = createHolding(holdingName,currentPrice);
        h.setCountShares(stocksPurchased);
        h.setCostPrice(costPrice);
        portfolioDao.addHolding(portfolio,h);
        portfolio.addHolding(h);
        return h;
    }

    public Investor addNewInvestor(String portfolioName, String investorName, double amontInvested){
        Investor investor = context.getBean(Investor.class);
        Portfolio portfolio = portfolioMap.get(portfolioName);
        investor.setUnitsSold(amontInvested/portfolio.getNAV());
        investor.setAmountInvested(amontInvested);
        investor.setName(investorName);
        portfolioDao.addInvestor(portfolio,investor);

        portfolio.addInvestor(investor);
        return investor;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context =applicationContext;
    }
}

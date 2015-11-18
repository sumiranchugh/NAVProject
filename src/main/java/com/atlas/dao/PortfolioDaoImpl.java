package com.atlas.dao;

import com.atlas.assets.Holding;
import com.atlas.investors.Investor;
import com.atlas.portfolios.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by schug2 on 11/17/2015.
 */
@Repository
public class PortfolioDaoImpl implements PortflioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createPortfoio(Portfolio portfolio) {

        jdbcTemplate.update(QueryConstants.INSERT_PORTFOLIO, new Object[]{portfolio.getName(), portfolio.getNAV(), portfolio.getLiabilities()});
        jdbcTemplate.query(QueryConstants.SELECT_PORTFOLIO_BYNAME, new Object[]{portfolio.getName()}, (rs ->
        { rs.next();
            portfolio.setId(rs.getLong("ID"));
            return portfolio;
        }));

        return;

    }

    @Override
    public void addHolding(Portfolio portfolio, Holding holding) {


            jdbcTemplate.update(QueryConstants.INSERT_HOLDING_PHM, new Object[]{portfolio.getId(), holding.getId(), holding.getCountShares(), holding.getCostPrice()});
          //  return holding;



    }

    @Override
    public void updateNAV(Portfolio portfolio) {

    }

    @Override
    public Portfolio getPortfolio(Portfolio portfolio) {
        return null;
    }

    @Override
    public Investor addInvestor(Portfolio portfolio, Investor investor) {
        jdbcTemplate.update(QueryConstants.INSERT_INVESTOR_I, new Object[]{investor.getName(),investor.getName()});
        jdbcTemplate.query(QueryConstants.SELECT_INVESTOR_BYNAME,new Object[]{investor.getName()},(rs, rowNum) ->{
            investor.setId(rs.getLong("Id"));
            jdbcTemplate.update(QueryConstants.INSERT_INVESTOR_PIM, new Object[]{ investor.getId(),portfolio.getId(), investor.getAmountInvested(),investor.getUnitsSold()});
            return investor;});
        return investor;
    }

    @Override
    public Holding createHolding(String holdingName, double currentPrice) {
        Holding h = new Holding(holdingName,currentPrice,currentPrice);
        jdbcTemplate.update(QueryConstants.INSERT_HOLDING_H, new Object[]{holdingName, currentPrice, currentPrice});
        jdbcTemplate.query(QueryConstants.SELECT_HOLDING_BYNAME, new Object[]{h.getName()}, (rs, rowNum) -> {

            h.setId(rs.getLong(1));
            return h;
        });
        return h;
    }

}

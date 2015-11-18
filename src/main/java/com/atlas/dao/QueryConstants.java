package com.atlas.dao;

/**
 * Created by schug2 on 11/18/2015.
 */
public class QueryConstants {
    public static final String INSERT_PORTFOLIO = "INSERT INTO PORTFOLIO(NAME ,NAV,LIABILITY) VALUES (?,?,?)";
    public static final String INSERT_HOLDING_H = "INSERT INTO HOLDING(NAME,CURRENT_PRICE ,LAST_PRICE) VALUES (?,?,?)";
    public static final String INSERT_HOLDING_PHM= "INSERT INTO PHM(PORTFOLIO_ID,HOLDING_ID,stocks_Purchased ,Cost_Of_Purchase) VALUES (?,?,?,?)";
    public static final String SELECT_HOLDING_BYNAME ="SELECT * FROM HOLDING WHERE NAME =?";
    public static final String SELECT_PORTFOLIO_BYNAME = "SELECT * FROM PORTFOLIO WHERE NAME =?";
    public static final String INSERT_INVESTOR_I = "INSERT INTO INVESTOR(Name,DESC) values (?,?)";
    public static final String SELECT_INVESTOR_BYNAME = "SELECT * FROM INVESTOR WHERE NAME=?";
    public static final String INSERT_INVESTOR_PIM = "INSERT INTO PIM(investor_Id,portfolio_Id,amount_Invested,units_Bought) values (?,?,?,?)";
}

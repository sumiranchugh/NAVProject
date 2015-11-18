package com.atlas.portfolios.report;

import com.atlas.portfolios.Portfolio;
import org.springframework.stereotype.Component;

/**
 * Created by schug2 on 11/18/2015.
 */
@Component
public class SummaryReport implements Report {


    @Override
    public void print(Portfolio portfolio) {
        System.out.println( portfolio);
    }
}

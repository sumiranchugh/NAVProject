package com.atlas.events.eventpublisher;

import com.atlas.assets.Holding;
import com.atlas.events.PriceChangeEvent;
import com.atlas.portfolios.PortfolioManager;
import com.atlas.pricemanager.ChangeEventListner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by schug2 on 11/18/2015.
 */
@Component
public class PricePublisher implements Publisher<Holding,PriceChangeEvent> {

    public Set<ChangeEventListner<PriceChangeEvent>> set = new HashSet<>();

    private  PriceChangeEvent e;

    @Autowired
    private PortfolioManager portfolioManager;


    @Override
    public synchronized void attach(Holding holding) {
       set.add(holding);
    }

    @Override
    public synchronized void publish() {
        System.out.println("Publishing");
        set.forEach((p)->
                        p.processChange(e)
        );
           portfolioManager.getPortfolioSummary("P1");
    }

    @Override
    public synchronized void setEvent(PriceChangeEvent priceChangeEvent) {
        this.e=priceChangeEvent;
    }




}

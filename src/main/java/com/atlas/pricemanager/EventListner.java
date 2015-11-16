package com.atlas.pricemanager;

import com.atlas.events.Event;
import com.atlas.events.PriceChangeEvent;
import com.atlas.portfolios.PortfolioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by schug2 on 11/16/2015.
 */
@Component
public class EventListner {


    @Autowired
    private PortfolioManager portfolioManager;

    @JmsListener(destination = "events-destination", containerFactory = "myJmsContainerFactory")
    public void receiveEvent(Event message) {
        System.out.println("Received <" + message + ">");

        switch (message.getType()) {

            case PRICECHANGE: {
                //updatePrice for Holding
                PriceChangeEvent event = (PriceChangeEvent) message;
                portfolioManager.getAllPortfolios().stream().forEach((portfolio -> {
                    portfolio.getHoldings().stream().filter((holding -> holding.getName().equals(message.getSource()))).forEach((h) -> {
                        h.setSharePrice(event.getNewPrice());
                        h.setOldPrice(event.getOldPrice());
                    });
                    System.out.println(portfolio);
                }
                ));
            }
        }



    }
}

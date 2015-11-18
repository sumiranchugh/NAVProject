package com.atlas;

import com.atlas.events.Event;
import com.atlas.events.PriceChangeEvent;
import com.atlas.events.eventpublisher.EventPublisher;
import com.atlas.events.eventpublisher.helper.Constants;
import com.atlas.portfolios.Portfolio;
import com.atlas.portfolios.PortfolioManager;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * Created by schug2 on 11/16/2015.
 */
@SpringBootApplication
public class Application {

    @Bean(name = "cf")
    public ConnectionFactory getCF() {
        return new ActiveMQConnectionFactory(Constants.JMS_URL);
    }

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        PortfolioManager manager = context.getBean(PortfolioManager.class);
        //  SamplePortfolio portfolio =context.getBean(SamplePortfolio.class);

        Portfolio portfolio1 = manager.createPortfolio("P1", 10, 0);
        manager.addNewHolding(portfolio1.getName(), "H1", 10, 100, 10);
        manager.addNewInvestor(portfolio1.getName(), "I1", 500);

        manager.getPortfolioSummary(portfolio1.getName());


        EventPublisher publisher = context.getBean(EventPublisher.class);

        PriceChangeEvent event = context.getBean(PriceChangeEvent.class);


        messageSenderNJ(publisher, event);


    }

    @Bean
    public PriceChangeEvent getPCE() {
        return new PriceChangeEvent("H1", "PM", 15.00, 9.00);
    }

    private static void messageSenderNJ(EventPublisher producer, Event event) {


        try {
            producer.send(event);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
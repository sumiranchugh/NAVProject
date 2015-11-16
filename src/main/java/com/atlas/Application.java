package com.atlas;

import com.atlas.assets.Holding;
import com.atlas.events.PriceChangeEvent;
import com.atlas.investors.Investor;
import com.atlas.portfolios.Portfolio;
import com.atlas.portfolios.PortfolioManager;
import com.atlas.portfolios.SamplePortfolio;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by schug2 on 11/16/2015.
 */
@SpringBootApplication
@EnableJms
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        PortfolioManager manager = context.getBean(PortfolioManager.class);
        SamplePortfolio portfolio =context.getBean(SamplePortfolio.class);

        Investor investor = context.getBean(Investor.class);
        investor.setAmountInvested(10000);
        investor.setStartDate(new Date());
        investor.setEndDate(new Date());


        Holding holding1 = context.getBean(Holding.class);
        Holding holding2 = context.getBean(Holding.class);
        initHoldings(holding1,holding2);



        portfolio.setName("Portfolio 1");
        portfolio.addHolding(holding1);
        portfolio.addHolding(holding2);
        portfolio.addInvestor(investor);
        manager.addPortfolio(portfolio);
        System.out.println(portfolio);

        messageSender(context);



    }

    private static void initHoldings(Holding ... H1){
        int i=0;
        for(Holding h : H1){
i++;
            h.setSharePrice(Math.random()*100);
            h.setName("H"+i);
            h.setOldPrice(h.getSharePrice());
            h.setCountShares(100);
        }
    }

    private static void messageSender(ConfigurableApplicationContext context) {
        // Send a message
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(new PriceChangeEvent("H1", "PM", 15.00, 9.00));
            }
        };
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending a new message.");
        jmsTemplate.send("events-destination", messageCreator);
    }

    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
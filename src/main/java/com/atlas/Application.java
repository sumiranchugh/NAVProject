package com.atlas;

import com.atlas.assets.Holding;
import com.atlas.events.eventListner.EventListner;
import com.atlas.events.eventpublisher.EventPublisher;
import com.atlas.events.eventpublisher.PricePublisher;
import com.atlas.events.eventpublisher.helper.Constants;
import com.atlas.events.Event;
import com.atlas.events.PriceChangeEvent;
import com.atlas.investors.Investor;
import com.atlas.portfolios.Portfolio;
import com.atlas.portfolios.PortfolioManager;
import com.atlas.portfolios.SamplePortfolio;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.Date;

/**
 * Created by schug2 on 11/16/2015.
 */
@SpringBootApplication
public class Application {

    @Bean(name = "cf")
    public ConnectionFactory getCF(){
       return new ActiveMQConnectionFactory(Constants.JMS_URL);
    }

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        PortfolioManager manager = context.getBean(PortfolioManager.class);
      //  SamplePortfolio portfolio =context.getBean(SamplePortfolio.class);

       Portfolio portfolio1 = manager.createPortfolio("P1", 10, 0);
        manager.addNewHolding(portfolio1.getName(),"H1",10,100,10);
        manager.addNewInvestor(portfolio1.getName(),"I1",5000);
       /* Investor investor = context.getBean(Investor.class);
        investor.setAmountInvested(10000);
        investor.setStartDate(new Date());
        investor.setEndDate(new Date());


        Holding holding1 = context.getBean(Holding.class);
        Holding holding2 = context.getBean(Holding.class);
        initHoldings(holding1,holding2);



        portfolio.setName("Portfolio 1");
        portfolio.addHolding(holding1);
        portfolio.addHolding(holding2);
        portfolio.addInvestor(investor);*/
     //   manager.addPortfolio(portfolio);
        manager.getPortfolioSummary(portfolio1.getName());


        EventPublisher publisher =context.getBean(EventPublisher.class);
       // PricePublisher pricePublisher = context.getBean(PricePublisher.class);

        PriceChangeEvent event = context.getBean(PriceChangeEvent.class);




      //  messageSender(context);
        messageSenderNJ(publisher, event);
      //  System.out.println(pricePublisher.set.containsAll(portfolio1.getHoldings()));
        manager.getPortfolioSummary(portfolio1.getName());
     //   context.getBean(EventListner.class).shutDownHook=true;
       // context.close();




    }

    @Bean
    public   PriceChangeEvent getPCE() {
        return new PriceChangeEvent("H1", "PM", 15.00, 9.00);
    }

    private static void messageSenderNJ(EventPublisher producer,Event event) {
   //    EventListner eventListner= new EventListner();
     // eventListner.init();


        try {
            producer.send(event);
        } catch (JMSException e) {
            e.printStackTrace();
        }
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


}
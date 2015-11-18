package com.atlas.events.eventListner;

import com.atlas.events.PriceChangeEvent;
import com.atlas.events.eventpublisher.Publisher;
import com.atlas.events.eventpublisher.helper.Constants;
import com.atlas.events.Event;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;
import java.util.concurrent.*;
import javax.jms.BytesMessage;

/**
 * Created by schug2 on 11/17/2015.
 */
@Component
@DependsOn(value = {"eventPublisher"})
public class EventListner implements MessageListener {

    //@Autowired
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination queue;

    public static final  BlockingQueue<Event> events = new SynchronousQueue<>(true);

    public  volatile boolean shutDownHook = false;

    @Autowired
    Set<Publisher> publisherSet;


    @PostConstruct
    public void init() {
        try {
             connectionFactory = new ActiveMQConnectionFactory(Constants.JMS_URL);
            connection =connectionFactory.createConnection();
          //  connection.start();
            session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        //    topic = new ActiveMQTopic(Constants.TOPIC);
            queue=session.createQueue(Constants.QUEUE);
            // DurableTopicSubscription subscription = session.createDurableSubscriber(topic,)

            MessageConsumer consumer = session.createConsumer(queue);
         //   connection.start();
            consumer.setMessageListener(this);
           connection.start();
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    while (!shutDownHook) {
                        Event e =   events.take();
                        for (Publisher publisher : publisherSet) {
                            publisher.setEvent(e);
                            publisher.publish();
                        }


                    }
                    connection.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        if(message instanceof ObjectMessage) {
            ObjectMessage m = (ObjectMessage) message;

            try {
                Event e = (Event) m.getObject();
             events.put(e);
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(message instanceof TextMessage) {
            try {
                System.out.println(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
            else if(message instanceof BytesMessage){
                BytesMessage bm = (BytesMessage) message;

                try {
                    byte[] byteAr =new byte[(int)bm.getBodyLength()];
                    System.out.println(bm.readBytes(byteAr));
                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteAr));
                    Event event = (Event)objectInputStream.readObject();
                    events.put(event);
                } catch (JMSException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

        try {
            message.acknowledge();
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }



}

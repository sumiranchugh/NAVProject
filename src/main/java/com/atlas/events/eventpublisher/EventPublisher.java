package com.atlas.events.eventpublisher;

import com.atlas.events.eventpublisher.helper.Constants;
import com.atlas.events.Event;
import org.apache.activemq.ActiveMQConnectionFactory;

import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URI;

/**
 * Created by schug2 on 11/17/2015.
 */
@Component
public class EventPublisher  {

    //@Autowired
    private ConnectionFactory connectionFactory;

    private Connection connection;

    private Destination queue;

    private Session session;

    MessageProducer producer;


   // @PostConstruct
    public void init() {



    }


    public void send(Event event) throws JMSException {
        connectionFactory= new ActiveMQConnectionFactory(URI.create(Constants.JMS_URL));

        try {
            connection =connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true,Session.CLIENT_ACKNOWLEDGE);
            queue= session.createQueue(Constants.QUEUE);

            producer = session.createProducer(queue);


        } catch (JMSException e) {
            e.printStackTrace();
        }
       BytesMessage message= session.createBytesMessage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(event);
            byte[] bytes = bos.toByteArray();
            message.writeBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }


        producer.send(
                message, DeliveryMode.PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
        session.commit();
        session.close();
    }
}

package com.core.model.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by i.vartanian on 25.12.2014.
 */
@MessageDriven(name = "MessageReportEJB", mappedName = "jms.ForumTopic",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "jms.TopicFactory"),
                @ActivationConfigProperty(propertyName = "destinationName", propertyValue = "jms.ForumTopic"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
        })
public class MessageReportBean implements MessageListener {
    public MessageReportBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("URA!!!!!!!!!!!!!!!!!!!!" + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

//        RTFProcessor processor = null;
//        DataProcessor dataProcessor = null;

    }
}

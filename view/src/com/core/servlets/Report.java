package com.core.servlets;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by i.vartanian on 25.12.2014.
 */
@WebServlet(name = "Report", urlPatterns = "/report")
public class Report extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Context context = null;
        TopicConnectionFactory topicConnectionFactory = null;
        Topic topic = null;
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        try {

            context = new InitialContext();
            topicConnectionFactory = (TopicConnectionFactory) context.lookup("jms.TopicFactory");
            topic = (Topic) context.lookup("jms.ForumTopic");

            connection = topicConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(topic);

            TextMessage textMessage = session.createTextMessage("Create 10 messages");

            messageProducer.send(textMessage);

            System.out.println("Report creating");

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                if (messageProducer != null) {
                    try {
                        messageProducer.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }
}

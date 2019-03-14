/*
package com.wj.taotao.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class TestActivemqConsumer {

    @Test
    public void receive() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.0.0.116:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queue-test");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消息中的内容为：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void receive2() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.0.0.116:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageConsumer consumer = session.createConsumer(topic);

        while(true){
            Message message = consumer.receive(100000);
            if(null == message){
                break;
            }

            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("消息中的内容为：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

        }

        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void receive3() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.0.0.116:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageConsumer consumer = session.createConsumer(topic);

        while(true){
            Message message = consumer.receive(100000);
            if(null == message){
                break;
            }

            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("消息中的内容为：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

        }

        consumer.close();
        session.close();
        connection.close();
    }
}
*/

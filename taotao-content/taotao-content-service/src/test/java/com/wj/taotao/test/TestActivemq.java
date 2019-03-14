package com.wj.taotao.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class TestActivemq {


    @Test
    public void test1() throws JMSException {
        //1.创建ConnectionFactory对象，需要制定ip和端口（MQ中间件的地址）
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.0.0.116:61616");
        //2.使用connectionFactory创建一个connection对象
        Connection connection = connectionFactory.createConnection();
        //3.开启连接，调用connection的start方法
        connection.start();
        //4.使用connection创建一个session对象，第一个参数：表示是否使用分布式事务（JTA），true：开启事务，第二个参数：略
        //当第一个参数为false时，第二参数才有意义，消息的应答方式。1.自动应答2.手动应答 一般是自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.使用session创建一个Destination对象（topic，queue），此处创建一个queue对象，参数：队列的名称
        Queue queue = session.createQueue("queue-test");
        //6.使用session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(queue);
        //7.创建一个Message对象，TextMessage对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        TextMessage textMessage = session.createTextMessage("hello activemq ");
        //8.使用producer发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void test2() throws Exception{
        // 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
        //brokerURL服务器的ip及端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.0.0.116:61616");
        // 第二步：使用ConnectionFactory对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        // 第三步：开启连接，调用Connection对象的start方法。
        connection.start();
        // 第四步：使用Connection对象创建一个Session对象。
        //第一个参数：是否开启事务。true：开启事务，第二个参数忽略。
        //第二个参数：当第一个参数为false时，才有意义。消息的应答模式。1、自动应答2、手动应答。一般是自动应答。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
        //参数：队列的名称。
        Queue queue = session.createQueue("queue-test");
        // 第六步：使用Session对象创建一个Producer对象。
        MessageProducer producer = session.createProducer(queue);
        // 第七步：创建一个Message对象，创建一个TextMessage对象。
		/*TextMessage message = new ActiveMQTextMessage();
		message.setText("hello activeMq,this is my first test.");*/
        TextMessage textMessage = session.createTextMessage("hello activeMq,this is my first test.");
        // 第八步：使用Producer对象发送消息。
        producer.send(textMessage);
        // 第九步：关闭资源。
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopic() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.0.0.116:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("hello activemq topic");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

}

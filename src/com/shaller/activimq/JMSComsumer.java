package com.shaller.activimq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSComsumer {

	private static final String USER = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String[] args) {
		ConnectionFactory connectionFactory;// 连接工厂
		Connection connection = null;// 连接
		Session session;// 会话 接收或者发送消息的线程
		Destination destination;// 消息的目的地
		MessageConsumer messageConsumer;// 消息的消费者

		connectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, BROKERURL);
		try {
			connection = connectionFactory.createConnection();// 通过连接工厂获取连接
			connection.start();// 启动连接
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("testMq"); // 创建连接的消息队列
			messageConsumer = session.createConsumer(destination);// 创建消息消费者
			while (true) {
				TextMessage message = (TextMessage) messageConsumer.receive(10000);
				if (message != null) {
					System.out.println("收到的消息为：" + message.getText());
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}

	}
}

package com.shaller.activimq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 
 * @ClassName: JMSConsumerByListener
 * @Description: 监听模式 不需要手动关闭connection
 * @author Panshaller
 * @date 2017年11月5日 下午11:00:51
 *
 */
public class JMSConsumerByListener {
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
			messageConsumer.setMessageListener(new Listener());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

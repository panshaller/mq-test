package com.shaller.activimq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// 默认用户名
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// 默认密码
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// 默认连接地址
	private static final int SENDNUM = 20;// 发送的消息数量

	public static void main(String[] args) {
		ConnectionFactory connectionFactory; // 连接工厂
		Connection connection = null; // 连接
		Session session; // 回话 接收或者发送消息的线程
		Destination destination; // 发送目的地
		MessageProducer messageProducer; // 消息生产者

		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL); // 通过连接工厂获取连接

		try {
			connection = connectionFactory.createConnection();
			connection.start();// 启动连接
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);// 自动确认
			destination = session.createQueue("testMq");//创建队列
			messageProducer = session.createProducer(destination);//创建生产者
			sendMessage(session, messageProducer);
			session.commit();
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

	/**
	 * @Title: sendMessage 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param session 
	 * @param @param messageProducer 
	 * @param @throws JMSException 设定文件
	 * @param void 返回类型
	 * @throws
	 */
	private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
		for (int i = 0; i < SENDNUM; i++) {
			TextMessage message = session.createTextMessage("发送消息:这是第" + i + "条！");
			System.out.println(message.getText());
			messageProducer.send(message);
		}
	}

}

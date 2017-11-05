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
		ConnectionFactory connectionFactory;// ���ӹ���
		Connection connection = null;// ����
		Session session;// �Ự ���ջ��߷�����Ϣ���߳�
		Destination destination;// ��Ϣ��Ŀ�ĵ�
		MessageConsumer messageConsumer;// ��Ϣ��������

		connectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, BROKERURL);
		try {
			connection = connectionFactory.createConnection();// ͨ�����ӹ�����ȡ����
			connection.start();// ��������
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("testMq"); // �������ӵ���Ϣ����
			messageConsumer = session.createConsumer(destination);// ������Ϣ������
			while (true) {
				TextMessage message = (TextMessage) messageConsumer.receive(10000);
				if (message != null) {
					System.out.println("�յ�����ϢΪ��" + message.getText());
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

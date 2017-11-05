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

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// Ĭ���û���
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// Ĭ������
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// Ĭ�����ӵ�ַ
	private static final int SENDNUM = 20;// ���͵���Ϣ����

	public static void main(String[] args) {
		ConnectionFactory connectionFactory; // ���ӹ���
		Connection connection = null; // ����
		Session session; // �ػ� ���ջ��߷�����Ϣ���߳�
		Destination destination; // ����Ŀ�ĵ�
		MessageProducer messageProducer; // ��Ϣ������

		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL); // ͨ�����ӹ�����ȡ����

		try {
			connection = connectionFactory.createConnection();
			connection.start();// ��������
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);// �Զ�ȷ��
			destination = session.createQueue("testMq");//��������
			messageProducer = session.createProducer(destination);//����������
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
	 * @Description: TODO(������һ�仰�����������������) 
	 * @param @param session 
	 * @param @param messageProducer 
	 * @param @throws JMSException �趨�ļ�
	 * @param void ��������
	 * @throws
	 */
	private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
		for (int i = 0; i < SENDNUM; i++) {
			TextMessage message = session.createTextMessage("������Ϣ:���ǵ�" + i + "����");
			System.out.println(message.getText());
			messageProducer.send(message);
		}
	}

}

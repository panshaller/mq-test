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
 * @Description: ����ģʽ ����Ҫ�ֶ��ر�connection
 * @author Panshaller
 * @date 2017��11��5�� ����11:00:51
 *
 */
public class JMSConsumerByListener {
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
			messageConsumer.setMessageListener(new Listener());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

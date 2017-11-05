package com.shaller.activimq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 
 * @ClassName: Listener
 * @Description: ��Ϣ����
 * @author Panshaller
 * @date 2017��11��5�� ����10:49:34
 *
 */
public class Listener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		try {
			System.out.println("�յ�����ϢΪ��" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}

package com.shaller.activimq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 
 * @ClassName: Listener
 * @Description: 消息监听
 * @author Panshaller
 * @date 2017年11月5日 下午10:49:34
 *
 */
public class Listener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		try {
			System.out.println("收到的消息为：" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}

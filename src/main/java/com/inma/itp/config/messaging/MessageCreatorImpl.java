/**
 * 
 */
package com.inma.itp.config.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.destination.DestinationResolver;

/**
 * @author ssatwa
 * 
 */
public class MessageCreatorImpl implements MessageCreator {

	private TextMessage message;
	private String msg;
	private String[] queues;
	DestinationResolver destinatinoResolver;

	public MessageCreatorImpl(DestinationResolver destinatinoResolver, String[] queues, String msg) {
		this.msg = msg;
		this.queues = queues;
		this.destinatinoResolver = destinatinoResolver;
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		message = session.createTextMessage();
		message.setText(msg);

		message.setJMSReplyTo(destinatinoResolver.resolveDestinationName(session, "ETRD." + queues[1], false));

		return message;
	}

	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(TextMessage message) {
		this.message = message;
	}

}

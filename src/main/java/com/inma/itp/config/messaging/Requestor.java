package com.inma.itp.config.messaging;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.inma.itp.config.exception.MqException;

import lombok.extern.slf4j.Slf4j;

/**
 * Class responsible to send and receive message from MQ
 * 
 * @author ssatwa
 *
 */
@Slf4j
@Component
public class Requestor {

	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * Send Message with unique ID to MQ and receive message filtered with this
	 * unique ID
	 * 
	 * @param msg
	 * @param queues
	 * @return
	 */
	public String request(final String msg, String[] queues) {
		try {

			MessageCreatorImpl messageCreator = new MessageCreatorImpl(jmsTemplate.getDestinationResolver(), queues,
					msg);
			// Formulate the MQ request message and send it.
			jmsTemplate.send("ETRD." + queues[0], messageCreator);

			Message backendReqMessage = messageCreator.getMessage();

			String selectedMsgId = "JMSCorrelationID='" + backendReqMessage.getJMSMessageID() + "'";

			Message respMsg = jmsTemplate.receiveSelected("ETRD." + queues[1], selectedMsgId);

			TextMessage responseMessage = (TextMessage) respMsg;

			log.info("Response: {}", responseMessage.getText());
			return responseMessage.getText();
		} catch (Exception ex) {
			throw new MqException(ex.getMessage());
		} finally {
			// Don't forget to close your resources
		}
	}
}
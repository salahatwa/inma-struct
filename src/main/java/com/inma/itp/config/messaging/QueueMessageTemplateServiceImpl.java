package com.inma.itp.config.messaging;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inma.itp.config.annotations.InmaQueue;
import com.inma.itp.config.annotations.LogExecutionTime;
import com.inma.itp.config.exception.MqException;

import lombok.extern.slf4j.Slf4j;

/**
 * Generice Template Service implementation if isOffline equal false Send
 * message to queue and get response from queue
 * 
 * @author ssatwa
 *
 */
@Slf4j
@Service("queueMessageTemplateServiceImpl")
public class QueueMessageTemplateServiceImpl implements MessageTemplateService {

	@Autowired
	private Requestor requestor;

	@LogExecutionTime
	@Override
	public <T, R> T sendMessage(R senderObject, Class<T> receivedObject) {
		String rqMsg = MessageSerializerHelper.serializeToXML(senderObject);
		log.info("Sending message \n{}", rqMsg);
		// Implementation to send message to Queue and get response
		String resp = requestor.request(rqMsg, getQueues(senderObject));
		log.info("Received message \n{}", resp);

		MessageValidationHelper.validate(resp);
		
		T response = MessageSerializerHelper.deserializeFromXML(receivedObject, resp);
		return response;
	}

	/**
	 * Get Sender and response for Queues name if classes annotated with InmaQueue
	 * Else return sender queue in as class name And return response queue as class
	 * name +'Rs'
	 * 
	 * @param object
	 * @return
	 */
	private String[] getQueues(Object object) {
		String[] classQueues = new String[2];
		try {
			InmaQueue queues = object.getClass().getAnnotation(InmaQueue.class);
			classQueues[0] = queues.requestQueue();
			classQueues[1] = queues.responseQueue();

			return classQueues;
		} catch (Exception ex) {
			classQueues = new String[2];
			String className = object.getClass().getSimpleName();
			classQueues[0] = className;
			classQueues[1] = className.substring(0, className.length() - 2) + "Rs";
			return classQueues;
		}
	}
}

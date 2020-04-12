package com.inma.itp.config.messaging;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.inma.itp.config.annotations.LogExecutionTime;
import com.inma.itp.config.exception.MqException;

import lombok.extern.slf4j.Slf4j;

/**
 * Generice Template Service implementation if isOffline equal true Load message
 * from offline-msg under resources folder
 * 
 * @author ssatwa
 *
 */
@Slf4j
@Service("offlineMessageTemplateServiceImpl")
public class OfflineMessageTemplateServiceImpl implements MessageTemplateService {

	@LogExecutionTime
	@Override
	public <T, R> T sendMessage(R senderObject, Class<T> receivedObject) {
		String rqMsg = MessageSerializerHelper.serializeToXML(senderObject);
		log.info("Sending message \n{}", rqMsg);
		String funcId = StringUtils.substringBetween(rqMsg, "<FuncId>", "</FuncId>");
		T response = MessageSerializerHelper.deserializeFromXML(receivedObject, getFakeRes(senderObject, funcId));

		log.info("Received message \n{}", MessageSerializerHelper.serializeToXML(response));
		return response;
	}

	/**
	 * offline response should be under src/main/resources/offline-msg File name
	 * should be [ requestName-FunctionId.xml ]
	 * 
	 * @param object
	 * @return
	 */
	private String getFakeRes(Object object, String funcId) {
		try {
			String objectName = object.getClass().getSimpleName();
			String fileName = objectName.substring(0, objectName.length() - 2) + "Rs-" + funcId + ".xml";

			Resource resource = new ClassPathResource("offline-msg/" + fileName);
			InputStream inputStream = resource.getInputStream();
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			String xmlMsg = new String(bdata, StandardCharsets.UTF_8);
			return xmlMsg;
		} catch (Exception ex) {
			log.error("Error File not found {}" + ex.getMessage());
		}
		return null;
	}

}

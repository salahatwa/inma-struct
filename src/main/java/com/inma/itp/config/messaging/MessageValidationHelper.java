package com.inma.itp.config.messaging;

import org.apache.commons.lang.StringUtils;

import com.inma.itp.config.exception.MqException;

/**
 * Message validation
 * @author ssatwa
 *
 */
public class MessageValidationHelper {
	public final static String SUCCESS = "I000000";

	public static void validate(String respMsg) {
		String statusCode = StringUtils.substringBetween(respMsg, "<StatusCode>", "</StatusCode>");

		if (!statusCode.equals(SUCCESS))
			throw new MqException(statusCode);
	}

}

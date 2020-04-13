package com.inma.itp.config.utils;

import java.util.UUID;

import com.inma.itp.config.ApplicationContextProvider;

/**
 * singleton for generating Random Unique UUID
 * 
 * @author ssatwa
 *
 */
public class GenerateShortUUID {

	private static String channel = ApplicationContextProvider.getEnvironmentProperty("inma.channel", String.class,
			"");

	public static String id() {
		return channel + UUID.randomUUID().toString().replace("-", "");
	}

	public static String getChannel() {
		return channel;
	}

}

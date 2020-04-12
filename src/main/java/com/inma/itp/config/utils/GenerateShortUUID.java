package com.inma.itp.config.utils;

import java.util.UUID;

import org.springframework.util.DigestUtils;

/**
 * singleton for generating Random Unique UUID
 * 
 * @author ssatwa
 *
 */
public class GenerateShortUUID {

	public static String id() {
		String id = "";
		String ts = String.valueOf(System.currentTimeMillis());
		String rand = UUID.randomUUID().toString();
		id = String.format("%s", DigestUtils.md5DigestAsHex((ts + rand).getBytes()));
		return id;
	}

}

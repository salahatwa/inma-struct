package com.inma.itp.config.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author M. Ali Hammam
 * 
 */
public class Security {

	public static byte[] getHash(String password) {
		if (password == null || password.trim().equals(""))
			return new byte[0];
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.reset();
		byte[] input = digest.digest(password.getBytes());
		return input;
	}

	public static String byteToHex(byte[] data) {
		if (data == null) {
			return null;
		}
		String hexa = "";
		for (int i = 0; i < data.length; i++) {
			hexa += byteToHex(data[i]);
		}
		return hexa;
	}

	public static String byteToHex(byte data) {
		StringBuffer buf = new StringBuffer();
		buf.append(toHexChar((data >>> 4) & 0x0F));
		buf.append(toHexChar(data & 0x0F));
		return buf.toString();
	}

	public static byte[] hexToBytes(String str) {
		if (str == null || str.length() < 2)
			return null;
		else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++)
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			return buffer;
		}
	}

	/**
	 * Convenience method to convert an int to a hex char.
	 * 
	 * @param i the int to convert
	 * @return char the converted char
	 */
	public static char toHexChar(int i) {
		if ((0 <= i) && (i <= 9)) {
			return (char) ('0' + i);
		} else {
			return (char) ('a' + (i - 10));
		}
	}

	public static String encryptANSIPINBlock(String cardNumber, String pin, String key1, String key2) {
		String ret = null;
		try {
			int x, y = 0;
			StringBuffer theRetString = new StringBuffer();
			if (cardNumber != null && cardNumber.length() > 0 && pin != null && pin.length() > 0) {
				String cardNumberBlock = cardNumber.substring(0, cardNumber.length() - 1);
				if (cardNumberBlock.length() >= 12)
					cardNumberBlock = cardNumberBlock.substring(cardNumberBlock.length() - 12,
							cardNumberBlock.length());
				while (cardNumberBlock.length() < 16)
					cardNumberBlock = "0" + cardNumberBlock;
				String pinBlock = "0" + pin.length() + pin;
				while (pinBlock.length() < 16)
					pinBlock = pinBlock + "F";
				for (int i = 0; i < 16; i++) {
					// converting the hexadecimal string to integer(int)
					x = Integer.parseInt(pinBlock.substring(i, i + 1), 16);
					y = Integer.parseInt(cardNumberBlock.substring(i, i + 1), 16);
					// the toHexString method converts the integer(int) into
					// hexadecimal string
					theRetString.append(Integer.toHexString(x ^ y));
				}
				ret = theRetString.toString();
				byte[] s = hexToBytes(ret);
				byte[] keyBytes = hexToBytes(key1);
				byte[] keyBytes2 = hexToBytes(key2);
				KeySpec ks = new DESKeySpec(keyBytes);
				KeySpec ks2 = new DESKeySpec(keyBytes2);
				SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
				SecretKey ky = kf.generateSecret(ks);
				SecretKey ky2 = kf.generateSecret(ks2);
				Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
				cipher.init(Cipher.ENCRYPT_MODE, ky);
				byte[] first = cipher.doFinal(s);
				cipher = Cipher.getInstance("DES/ECB/NoPadding");
				cipher.init(Cipher.DECRYPT_MODE, ky2);
				byte[] second = cipher.doFinal(first);
				cipher = Cipher.getInstance("DES/ECB/NoPadding");
				cipher.init(Cipher.ENCRYPT_MODE, ky);
				byte[] third = cipher.doFinal(second);
				ret = byteToHex(third);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}

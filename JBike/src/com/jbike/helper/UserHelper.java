package com.jbike.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import javax.xml.bind.DatatypeConverter;

public class UserHelper {
	
	public static String generateRandomPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return UserHelper.digest(new Timestamp((new java.util.Date()).getTime()).toString()).substring(0, 8);
	}

	public static String digest(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		return DatatypeConverter.printHexBinary(md5.digest(input.getBytes("UTF-8")));
	}

}

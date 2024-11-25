package com.japhni81.jani.crypto.test;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

public class KeyLengthTester {

	public static void main(String[] args) {
		int maxKeyLen = 0;
		try {
			maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("MaxAllowedKeyLength=[" + maxKeyLen + "].");

	}

}

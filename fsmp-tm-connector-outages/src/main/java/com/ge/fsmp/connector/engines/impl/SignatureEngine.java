package com.ge.fsmp.connector.engines.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ge.fsmp.connector.engines.ISignatureEngine;

@Service 
public class SignatureEngine implements ISignatureEngine {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignatureEngine.class);
	@Override
	public String getSignature(String key, String secret, long epoch) {
		
		String text = key + secret + String.valueOf(epoch);
		MessageDigest md = null;
        try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
		}
		byte[] md5hash = new byte[32];
		try {
			md.update(text.getBytes("UTF-8"), 0, text.length());
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
		}
		md5hash = md.digest();
		return convertToHex(md5hash);
		
		
	}
	
	private String convertToHex(byte[] data){
		 
		   char[] symbols="0123456789ABCDEF".toCharArray();
		   char[] hexValue = new char[data.length * 2];
		 
		   for(int i=0;i<data.length;i++){
			    int current = data[i] & 0xff;
		        hexValue[i*2+1] = symbols[current & 0x0f];
			    hexValue[i*2] = symbols[current >> 4];
		    }
		   return hexValue.toString();
	}

	
}

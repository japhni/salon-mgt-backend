package com.japhni81.jani.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import jakarta.persistence.AttributeConverter;

import org.springframework.stereotype.Component;


@Component
public class StringAttributeConverter implements AttributeConverter<String, String> {

	 private static final String AES = "AES";
	    private static final String SECRET = "$)QxeR('@*0267TcxaWJaphni81@#YZX";

	    private final Key key;
	    private final Cipher cipher;

	    public StringAttributeConverter() throws Exception {
	        key = new SecretKeySpec(SECRET.getBytes(), AES);
	        cipher = Cipher.getInstance(AES);
	    }

	    @Override
	    public String convertToDatabaseColumn(String attribute) {
	    	
	    	if (attribute == null) {
	            return null;
	        }
	    	
	    	
	        try {
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
	        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
	            throw new IllegalStateException(e);
	        }
	    }

	    @Override
	    public String convertToEntityAttribute(String dbData) {
	    	
	    	if (dbData == null || dbData.isEmpty()) {
	    		
	            return null;
	        }
	    	
	        try {
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
	        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    
	 
}
	
/**
 * 
 */
package com.japhni81.jani.crypto.test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Japhet
 *
 */
public class TestSHA {

	/**
	 * @param args
	 */
	// Driver code 
    public static void main(String args[])
    { 
        try 
        {
        	System.out.println("\n Hachage du message à l'aide de SHA256: " );
   		 	System.out.println("\n -------------------------------------- " );
  
            String s = "Technologies de l'Information et de la Communication"; 
            System.out.println("\n Le message à hacher est: " + s);
            
            long x=System.currentTimeMillis();
            
            System.out.println("\n Le resultat du message haché est : " + toHexString(getSHA(s))+"\n");
            
            long y =System.currentTimeMillis();
            
            long c=y-x;
            
            System.out.println("\n Le temps utilisé: " + c +" ms");
            
           
        }
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown for incorrect algorithm: " + e); 
        } 
    }
    
    
	
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
	
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
    
    
    public static void testHashedMethod(String s, String text_hashed) {
    	
    	try {
    		if(toHexString(getSHA(s)).matches(text_hashed)) {
    			
    			System.out.println("Les hashs sont equivalent");
    		}else {
    			
    			System.out.println("Les hashs ne sont pas equivalent");
    		}
    		
    		
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
} 
	

/**
 * 
 */
package com.japhni81.jani.crypto.test;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Japhet
 *
 */

public class TestBcrypt {
	
	/*
	 * La méthode gensalt () prend un paramètre optionnel (log_rounds) qui détermine
	 * la complexité de calcul du hachage:
	 * 
	 * String strong_salt = BCrypt.gensalt(10) String stronger_salt =
	 * BCrypt.gensalt(12)
	 * 
	 * La quantité de travail augmente de façon exponentielle (2 ** log_rounds), de
	 * sorte que chaque incrément représente deux fois plus de travail. Le
	 * log_rounds par défaut est 10 et la plage valide est comprise entre 4 et 31.
	 */

	public static void main(String[] args) {
		
		 System.out.println("\n Hachage du message à l'aide de Bcrypt: " );
		 System.out.println("\n -------------------------------------- " );
		
		 String s = "Technologies de l'Information et de la Communication"; 
         System.out.println("\n Le message à hacher est: " + s);
         
         System.out.println("\n Facteur de coût égal: 16");
         
         long x=System.currentTimeMillis();
         
         cryptMethod(s);
         
         long y =System.currentTimeMillis();
         
         long c=y-x;
         
         System.out.println("\n Le temps utilisé: " + c +" ms");
         
			/*
			 * testHashedMethod("Université du Burundi"
			 * ,"$2a$10$npKo5Suw0OOz/ldiM3x7fO0iiTX/ve8Ivc3u.pZDDvJAuUjTnpav9");
			 */
         

	}
	
	public static void cryptMethod(String plain_text) {
		
		/* String pw_hash = */BCrypt.hashpw(plain_text, BCrypt.gensalt(13));
		
		/* System.out.println("\n Le resultat du message haché est : " + pw_hash); */
	
	}
	
	public static void testHashedMethod(String plain_text, String text_hashed) {
		
		if (BCrypt.checkpw(plain_text, text_hashed))
		    System.out.println("It matches");
		else
		    System.out.println("It does not match");
	
	 }

}

package pl.kwi.chrisblog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class HashUtils {
	
	/**
	 * Method encodes text to SHA1.
	 * 
	 * @param text object String which should be encoded
	 * @return object String with encoded text
	 * @throws NoSuchAlgorithmException
	 */
	public static String encodeToSHA1(String text) throws NoSuchAlgorithmException{
		byte[] convertme = text.getBytes();
	    MessageDigest md = MessageDigest.getInstance("SHA-1"); 
	    return byteArray2Hex(md.digest(convertme));
	}

	private static String byteArray2Hex(final byte[] hash) {
	    Formatter formatter = new Formatter();
	    for (byte b : hash) {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();
	}

}

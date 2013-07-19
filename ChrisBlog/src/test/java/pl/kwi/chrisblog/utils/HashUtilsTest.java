package pl.kwi.chrisblog.utils;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class HashUtilsTest {

	@Test
	public void encodeToSHA1() throws NoSuchAlgorithmException {
		String text = "123456";
		String result = HashUtils.encodeToSHA1(text);
		assertEquals("7c4a8d09ca3762af61e59520943dc26494f8941b", result);
	}

}

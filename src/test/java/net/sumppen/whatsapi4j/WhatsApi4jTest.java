package net.sumppen.whatsapi4j;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class WhatsApi4jTest {

	private final Logger log = Logger.getLogger(WhatsApi4jTest.class);
	@Test
	public void testPbkdf2() throws Exception {
        WhatsApi whatsapi = new WhatsApi("12345678", "", "test");
        byte[] key = whatsapi.pbkdf2("PBKDF2WithHmacSHA1", "test".getBytes(), "1234".getBytes(), 16, 20, false);
        log.debug(new String(key));
        assertTrue("35e3945d26a46d5cb76e986e4ac305b857b9a56b".equals(new String(key)));
	}
	
	@Test
	public void testKeyStream() throws Exception {
		String key = "key";
		KeyStream in = new KeyStream(key.getBytes());
		KeyStream out = new KeyStream(key.getBytes());
		String data = "foo";
		byte[] encoded = in.encode(data.getBytes(), 0, data.length(),false);
		log.debug(WhatsApi.toHex(encoded));
		byte[] decoded = out.decode(encoded, 0, encoded.length);
		log.debug(new String(decoded));
		assertTrue(data.equals(new String(decoded)));
	}
	
	@Test
	public void testBase64Decode() throws Exception {
		String password = "+pRhR5WH/tt4pNG5uO+rkNqRPh4=";
        WhatsApi whatsapi = new WhatsApi("12345678", "", "test");
        byte[] resp = whatsapi.base64_decode(password);
        String str = WhatsApi.toHex(resp);
        log.debug(str);
        assertTrue(str.equals("fa9461479587fedb78a4d1b9b8efab90da913e1e"));
	}

	@Test
	public void testReadCountries() throws Exception {
        WhatsApi whatsapi = new WhatsApi("12345678", "", "test");
        List<Country> countries = whatsapi.getCountries();
        for(Country country : countries) {
        	log.debug(country);
        }
        assertEquals(254,countries.size());
	}
}

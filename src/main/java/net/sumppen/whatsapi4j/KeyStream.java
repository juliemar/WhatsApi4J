package net.sumppen.whatsapi4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class KeyStream {
	
	private final RC4 rc4;
	private byte[] key;
	private final Logger log = Logger.getLogger(KeyStream.class);

	public KeyStream(byte[] key) {
		rc4 = new RC4(key,256);
		this.key = key;
	}

	public byte[] encode(byte[] data, int offset, int length) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
		return encode(data, offset, length, true);
	}
	
	public byte[] encode(byte[] data, int offset, int length, boolean append) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		byte[] d = rc4.cipher(data, offset, length);
		byte[] hash_hmac = hash_hmac(d, key);
		byte[] h = Arrays.copyOfRange(hash_hmac, 0, 4);
		if(append) {
			stream.write(d);
			stream.write(h);
		} else {
			stream.write(h);
			stream.write(d);
		}
		return stream.toByteArray();
	}
	
	public static byte[] hash_hmac(byte[] d, byte[] mykey) throws NoSuchAlgorithmException, InvalidKeyException {
	    Mac mac = Mac.getInstance("HmacSHA1");
	    SecretKeySpec secret = new SecretKeySpec(mykey,"HmacSHA1");
	    mac.init(secret);
	    byte[] digest = mac.doFinal(d);
	    return digest;
	}

	public byte[] decode(byte[] data, int offset, int length) {
		return rc4.cipher(data, offset+4, length-4);
	}

	class RC4 {

		private int i;
		private int j;
		private byte[] s;
		private final Logger log = Logger.getLogger(RC4.class);

		public RC4(byte[] key, int drop) {
			s = range(0,255);
			for(int i1=0, j1=0; i1 < 256; ++i1) {
				int k = key[i1 % key.length] & 255;
				j1 = (j1+ k + (s[i1] & 255)) & 255;
				swap(i1,j1);
			}
			i=0;
			j=0;
			cipher(range(0,drop),0,drop);
		}
		
		private byte[] range(int k, int l) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			for(int ctr = k; ctr <= l; ++ctr) {
				stream.write(ctr);
			}
			return stream.toByteArray();
		}

		public byte[] cipher(byte[] data, int offset, int length) {
			ByteArrayOutputStream ret = new ByteArrayOutputStream();
	        for (int n = length; n > 0; n--) {
	            i = (i + 1) & 255;
	            j = (j + s[i]) & 255;
	            swap(i, j);
	            byte d = data[offset++];
	            ret.write( (d ^ s[(s[i] + s[j]) & 255]));
	        }
			return ret.toByteArray();
		}

		private void swap(int i2, int j2) {
			byte c = s[i2];
			s[i2] = s[j2];
			s[j2] = c;
		}
	}
}

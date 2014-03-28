package net.sumppen.whatsapi4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.ws.rs.HEAD;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class WhatsMediaUploader {

	private static final Logger log = Logger.getLogger(WhatsMediaUploader.class);

	public static JSONObject pushFile(ProtocolNode uploadResponseNode,
			Map<String, Object> messageContainer, File mediaFile, String selfJID) throws NoSuchAlgorithmException, IOException {
		//get vars
		String url = uploadResponseNode.getChild("media").getAttribute("url");
		String filepath = mediaFile.getName();
		String to = (String) messageContainer.get("to");
		return getPostString(filepath, url, mediaFile, to, selfJID);
	}

	private static JSONObject getPostString(String filepath, String url,
			File mediaFile, String to, String from) throws NoSuchAlgorithmException, IOException {
		URL u = new URL(url);
		String host = u.getHost();

		//filename to md5 digest
		String cryptoname = md5(filepath) + "." + mediaFile.getName().substring(mediaFile.getName().lastIndexOf('.'));
		String boundary = "zzXXzzYYzzXXzzQQ";
		int contentlength = 0;

		String hBAOS = "--" + boundary + "\r\n";
		hBAOS += "Content-Disposition: form-data; name=\"to\"\r\n\r\n";
		hBAOS += to + "\r\n";
		hBAOS += "--" + boundary + "\r\n";
		hBAOS += "Content-Disposition: form-data; name=\"from\"\r\n\r\n";
		hBAOS += from + "\r\n";
		hBAOS += "--" + boundary + "\r\n";
		hBAOS += "Content-Disposition: form-data; name=\"file\"; filename=\"" + cryptoname + "\"\r\n";
		hBAOS += "Content-Type: " + getMimeType(mediaFile) + "\r\n\r\n";

		String fBAOS = "\r\n--" + boundary + "--\r\n";

		contentlength += hBAOS.length();
		contentlength += fBAOS.length();
		contentlength += mediaFile.length();

		String POST = "POST " + url + "\r\n";
		POST += "Content-Type: multipart/form-data; boundary=" + boundary + "\r\n";
		POST += "Host: " + host + "\r\n";
		POST += "User-Agent: WhatsApp/2.3.53 S40Version/14.26 Device/Nokia302\r\n";
		POST += "Content-Length: " + contentlength + "\r\n\r\n";

		return sendData(host, POST, hBAOS, filepath, mediaFile, fBAOS);
	}

	private static JSONObject sendData(String host, String post, String head,
			String filepath, File mediaFile, String tail) throws IOException {
		Security.addProvider(
				new com.sun.net.ssl.internal.ssl.Provider());

		SSLSocketFactory factory = 
				(SSLSocketFactory)SSLSocketFactory.getDefault();
		SSLSocket socket = 
				(SSLSocket)factory.createSocket(host, 443);

		OutputStream out = socket.getOutputStream();
		out.write(post.getBytes());
		out.write(head.getBytes());

		//write file data
		FileInputStream fileInputStream = new FileInputStream(mediaFile);

		// Copy the contents of the file to the output stream
		byte[] buffer = new byte[1024];
		int count = 0;

		while ((count = fileInputStream.read(buffer)) >= 0) {
			out.write(buffer, 0, count);
		}                
		fileInputStream.close();
		out.write(tail.getBytes());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		String inputLine;
		StringBuilder data = new StringBuilder();
		while ((inputLine = in.readLine()) != null) 
			data.append(inputLine);
		in.close();
		out.close();
		socket.close();

		String[] parts = data.toString().split("\r\n\r\n");

		if(parts.length > 1) {
			try {
				JSONObject json = new JSONObject(parts[1]);
				if(json != null) {
					return json;
				}
			} catch (JSONException e) {
				log.warn("Invalid json returned from upload"+parts[1],e);
			}
		} else {
			log.warn("No JSON body found in response"+data.toString());
		}
		return null;
	}

	private static String getMimeType(File mediaFile) {
		return URLConnection.guessContentTypeFromName(mediaFile.getName());
	}

	private static String md5(String filepath) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		return new String(md5.digest(filepath.getBytes()));
	}

}

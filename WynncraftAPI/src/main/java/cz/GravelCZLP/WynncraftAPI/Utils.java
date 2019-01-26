package cz.GravelCZLP.WynncraftAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

public class Utils {

	public static String makeUrlGetRequest(URL url, HashMap<String, String> headers, boolean appendNewLine) {
		try {
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			headers.put("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:64.0) Gecko/20100101 Firefox/64.0");

			for (Entry<String, String> e : headers.entrySet()) {
				conn.addRequestProperty(e.getKey(), e.getValue());
			}

			conn.connect();

			if (conn.getResponseCode() == 404) {
				System.out.println("URL: " + url.toString() + " not found(404)");
				return null;
			}

			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line;

			while ((line = br.readLine()) != null) {
				if (appendNewLine) {
					sb.append(line + "\n");
				} else {
					sb.append(line);
				}
			}

			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}

package ru.ddosnik.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ru.ddosnik.VPechati;

public class Utils {

	public static String readUrl(String url) throws IOException {

    	URL uri = new URL(url);
        URLConnection conURl = (URLConnection) uri.openConnection();
        conURl.setConnectTimeout(2000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conURl.getInputStream(), "UTF-8"));
        String inputLine = in.readLine();
        in.close();

		return inputLine;

	}

	public static void checkToken() throws IOException {
		try {
		JSONObject response = (JSONObject) new JSONParser().parse(readUrl("https://api.vk.com/method/account.setOnline?access_token="+VPechati.vkToken+"&v=5.173"));
		if(response.get("error") != null) {
			JSONObject error = (JSONObject) response.get("error");
			System.out.println("> При проверке токена произошла ошибка: "+error.get("error_msg"));
			System.exit(0);
		} else {
			System.out.println("> Проверка токена прошла успешно.");
		}
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException pe) {
		pe.printStackTrace();
	}
}

  public static void connect(String url) throws IOException {

		URL uri = new URL(url);
        URLConnection conURl = (URLConnection) uri.openConnection();
        conURl.setConnectTimeout(2000);

        InputStreamReader in = new InputStreamReader(conURl.getInputStream(), "UTF-8");

        in.close();
	}

  public static String postRequest(String requestUrl, String body) {
      String answer = null;
      try {
          URL url = new URL(requestUrl);
          HttpURLConnection con = (HttpURLConnection) url.openConnection();

          con.setRequestMethod("POST");
          con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:100.0) Gecko/20100101 Firefox/100.0");
          con.setRequestProperty("Accept-Language", "UTF-8");

          con.setDoOutput(true);

          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
          outputStreamWriter.write(body);
          outputStreamWriter.flush();

          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
          String inputLine;
          StringBuffer response = new StringBuffer();

          while ((inputLine = in.readLine()) != null) {
              response.append(inputLine);
          }
          in.close();
          answer = response.toString();
      }catch(IOException e){
          e.printStackTrace();
      }

      return answer;
  }

  public static Integer getConversationId() {
  Integer id = VPechati.confId;
  return id;
  }

  public static String getToken() {
    String token = VPechati.vkToken;
    return token;
  }
}

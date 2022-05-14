package ru.ddosnik.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import ru.ddosnik.utils.Utils;

public class VKLib {
  private static String VKApi = "https://api.vk.com/method/";
  private static double VKApiV = 5.173;

  public static void setActivity() {
    try {
      String response = Utils.readUrl(VKApi + "messages.setActivity?access_token="+Utils.getToken()+"&v="+VKApiV+"&peer_id="+Utils.getConversationId()+"&type=typing");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void getConversations() {
    try {
      JSONObject response = (JSONObject) new JSONParser().parse(Utils.readUrl(VKApi + "messages.getConversations?access_token="+Utils.getToken()+"&v="+VKApiV+"&count=5"));
      JSONObject resp = (JSONObject) response.get("response");
      JSONArray items = (JSONArray) resp.get("items");
      for(int i = 0; i < items.size(); i++) {
        JSONObject json = (JSONObject) items.get(i);
        JSONObject conf = (JSONObject) json.get("conversation");
        JSONObject peer = (JSONObject) conf.get("peer");
        if(conf.get("chat_settings") == null) {
        System.out.println("> Обнаружен чат с пользователем, пропускаю.");
      } else {
        JSONObject chat = (JSONObject) conf.get("chat_settings");
        System.out.println("> Беседа "+chat.get("title")+", ID: "+peer.get("id"));
      }
  }
} catch (Exception e) {
			e.printStackTrace();
  }
}
}

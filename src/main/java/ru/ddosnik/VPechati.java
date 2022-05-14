package ru.ddosnik;

import ru.ddosnik.utils.VKLib;
import ru.ddosnik.utils.Utils;

import java.util.Scanner;
import java.io.IOException;

public class VPechati {
  public static String vkToken = "455866gkrkgk4a87944c181f1b603k43kkrkglf164fa074f0cgkrgkk4g5af265fb545c8e"; //your token on VK.
  public static Integer confId = 2000000000;

  public void Init() {
    try{
      System.out.println("> Скрипт VPechati запущен.");
      Utils.checkToken();
      VKLib.getConversations();
      Scanner in = new Scanner(System.in);
      System.out.print("Введите айди беседы: ");
      confId = in.nextInt();
      in.close();
      Work();
  } catch (IOException e) {
    e.printStackTrace();
  }
}

  public void Work() {
    try {
      while(true) {
        VKLib.setActivity();
        Thread.sleep(5000);
          }
      } catch(InterruptedException e) {
        System.out.println(e);
      }
}

  public static void main(String[] args) {
    VPechati vpc = new VPechati();
    vpc.Init();
  }
}

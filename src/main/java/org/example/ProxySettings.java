package org.example;

import java.util.Properties;

public class ProxySettings {
   public static void socks(String port){
       System.setProperty("proxySet","true");
       System.setProperty("socksProxyHost","localhost");
       System.setProperty("socksProxyPort",port);
   }
   public static void http(String port){
       System.setProperty("proxySet","true");
       System.setProperty("http.proxyHost", "127.0.0.1");
       System.setProperty("http.proxyPort", port);
   }
   public static void set(boolean set){
        System.setProperty("proxySet", String.valueOf(set));
    }
}

package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HttpURLConnectionUtil {
    /**
     * http get请求
     *
     * @param httpUrl 链接
     * @return 响应数据
     */
    public static String doGet(String httpUrl) {
        //链接
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //设置连接超时时间
            connection.setConnectTimeout(15000);
            //设置读取超时时间
            connection.setReadTimeout(15000);
            //开始连接
            connection.connect();
            //获取响应数据
            if (connection.getResponseCode() == 200) {
                //获取返回的数据
                is = connection.getInputStream();
                if (is != null) {
                    br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String temp = null;
                    while ((temp = br.readLine()) != null) {
                        result.append(temp);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
        return result.toString();
    }
    @Deprecated
    public static String getVideoUrl(String Url){
        String response = doGet(Url);
        String result=null;
        Pattern p = Pattern.compile("[a-zA-z]+://[^\\s]*mp4");
        Matcher m = p.matcher(response);
        if (m.find())  result=m.group();
        return result;
    }

}


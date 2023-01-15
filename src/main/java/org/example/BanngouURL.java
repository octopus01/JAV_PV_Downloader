package org.example;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class BanngouURL {
    /**
     * 支持的番号
     * supported:
     * faleno : fsdss flns flno
     * dmm:
     *   sod : stars
     *   s1 : ssni ssis ofje
     *   idea pocket: ipx
     *   Bazooka : MDBK
     *   moodyz : mide midv
     * prestige:
     *   abw
     *  以及更多
     */
    public static String getUrl(String banngou)  {
        String prefix = banngou.split("-")[0];
        prefix=prefix.toLowerCase();
        String id = banngou.split("-")[1];
        String tempId=id;
        String Url;
        String urlId;
        for (int i = 0; i < 5-id.length(); i++) {
            tempId="0"+tempId;
        }
        switch (prefix) {
            case "stars":
                urlId = "1" + prefix + id;
                Url = "https://cc3001.dmm.co.jp/litevideo/freepv/" + "1" + "/" + "1st" +
                        "/" + urlId + "/" + urlId + "_dmb_w.mp4";
                break;
            //faleno
            case "fsdss":
            case "flns":
            case "flno":
                //TODO
                //需要更新一下方法
                Url = HttpURLConnectionUtil.getVideoUrl("https://faleno.jp/top/works/" + prefix + id);
                break;
            case "abw":
                Url = "https://www.prestige-av.com/api/media/" + getABWUrl(banngou);
                break;
            case "mdbk":
                urlId = prefix + id;
                Url = "https://cc3001.dmm.co.jp/litevideo/freepv/" + prefix.charAt(0) + "/" + prefix.substring(0, 3) +
                        "/" + urlId + "/" + urlId + "_dmb_w.mp4";
                break;
            default:
                urlId = prefix + tempId;
                Url = "https://cc3001.dmm.co.jp/litevideo/freepv/" + prefix.charAt(0) + "/" + prefix.substring(0, 3) +
                        "/" + urlId + "/" + urlId + "_dmb_w.mp4";
                break;
        }
        return Url;
    }
    public static String getABWUrl(String banngou){
        JSONObject json;
        String response = HttpURLConnectionUtil.doGet("https://www.prestige-av.com/api/" +
                "search?isEnabledQuery=true&searchText="
                +banngou);

        json= JSON.parseObject(response);
        response= json.getJSONObject("hits").getJSONArray("hits").getJSONObject(0).
                getJSONObject("_source").getJSONObject("productMovie").getString("path");
        return response;
    }



}

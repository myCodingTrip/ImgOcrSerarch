package com.my.ocr;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 连接baidu ocr图片识别的工具类
 */
public class OcrUtil {
    //设置APPID/AK/SK
    private static final String APP_ID = "17156239";
    private static final String API_KEY = "ftaRgDzpZlp2zx0GqibpBt6i";
    private static final String SECRET_KEY = "HdYdlzjV13VSsY2VrzpWqVOoWI8ypdWV";

    // 初始化一个AipOcr
    private static final AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

    static {
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public static void main(String[] args) {


        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "e:\\图片\\6 影视\\无名之辈\\Screenshot_2019-09-24-19-41-14-451_com.youku.phone.png";
        String desc = getPicDesc(path);
        System.out.println(desc);
    }

    public static String getPicDesc(String path) {
        JSONObject res = client.basicGeneral(path, new HashMap<>());
        try {
            JSONArray arr = res.getJSONArray("words_result");
            List<String> descList = new ArrayList<String>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String words = String.valueOf(obj.get("words"));
                descList.add(words);
            }
            if (descList.size() == 0) return null;
            return String.join("\n", descList);
        } catch (Exception e) {
            System.out.println("error: " + path);
            System.out.println(res);
            e.printStackTrace();
            return null;
        }
    }
}
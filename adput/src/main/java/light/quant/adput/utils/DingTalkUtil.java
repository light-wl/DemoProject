package light.quant.adput.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author light
 * @date 2026/2/7
 * @desc 钉钉机器人工具类
 **/
public class DingTalkUtil {

    /**
     * 发送钉钉消息
     *
     * @param webhookUrl 钉钉机器人 webhook URL
     * @param title      消息标题
     * @param content    消息内容
     * @return 是否发送成功
     */
    public static boolean sendMessage(String webhookUrl, String title, String content) {
        try {
            // 构建消息体
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("msgtype", "markdown");
            
            Map<String, String> markdownMap = new HashMap<>();
            markdownMap.put("title", title);
            markdownMap.put("text", content);
            messageMap.put("markdown", markdownMap);
            
            String jsonBody = JSONObject.toJSONString(messageMap);
            
            // 发送请求
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(jsonBody.getBytes(), MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(webhookUrl)
                    .post(body)
                    .build();
            
            Response response = client.newCall(request).execute();
            
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println("钉钉消息发送成功: " + responseBody);
                return true;
            } else {
                System.out.println("钉钉消息发送失败，错误码: " + response.code());
                return false;
            }
        } catch (IOException e) {
            System.out.println("钉钉消息发送异常: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送文本消息
     *
     * @param webhookUrl 钉钉机器人 webhook URL
     * @param content    消息内容
     * @return 是否发送成功
     */
    public static boolean sendTextMessage(String webhookUrl, String content) {
        try {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("msgtype", "text");
            
            Map<String, String> textMap = new HashMap<>();
            textMap.put("content", content);
            messageMap.put("text", textMap);
            
            String jsonBody = JSONObject.toJSONString(messageMap);
            
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(jsonBody.getBytes(), MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(webhookUrl)
                    .post(body)
                    .build();
            
            Response response = client.newCall(request).execute();
            
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println("钉钉文本消息发送成功: " + responseBody);
                return true;
            } else {
                System.out.println("钉钉文本消息发送失败，错误码: " + response.code());
                return false;
            }
        } catch (IOException e) {
            System.out.println("钉钉文本消息发送异常: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}


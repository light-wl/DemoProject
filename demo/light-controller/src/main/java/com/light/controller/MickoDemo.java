package com.light.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.light.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author light
 * @date 2023/12/2 9:39
 * @desc
 */
@Slf4j
public class MickoDemo {
    public static String mainTableUrl = "https://automate.adjust.com/reports-service/report?full_data=true&attribution_source=first&ad_spend_mode=network&reattributed=all&attribution_type=all&readable_names=false&format_dates=false&metrics=installs%2Cattribution_clicks%2Clogin_d0_conversions_cohort%2Cfirst+purchase_d0_conversions_cohort%2Cpurchase_d0_conversions_cohort%2Cpurchase_d0_conversions_rate_cohort%2Call_revenue_total_d0&dimensions=channel&app_token__in=7jholop8bq80&cohort_maturity=immature&ironsource_mode=ironsource&sandbox=false&utc_offset=-03%3A00&date_period=today&coloring_mode=column&limit=5000&sort=-installs";
    public static String subTableUrl = "https://automate.adjust.com/reports-service/report?full_data=true&attribution_source=first&ad_spend_mode=network&reattributed=all&attribution_type=all&readable_names=false&format_dates=false&metrics=installs,attribution_clicks,login_d0_conversions_cohort,first+purchase_d0_conversions_cohort,purchase_d0_conversions_cohort,purchase_d0_conversions_rate_cohort,all_revenue_total_d0&dimensions=channel&app_token__in=7jholop8bq80&cohort_maturity=immature&ironsource_mode=ironsource&sandbox=false&utc_offset=-03:00&date_period=today&coloring_mode=column&limit=5000&sort=-installs";
    public static String cookie = "_hjSessionUser_2831967=eyJpZCI6ImJjN2ExMTFjLTVjYzEtNTFhZC05NjY0LTUzOTMxZjlhODg4MyIsImNyZWF0ZWQiOjE2OTE3MTcyNTYzMTAsImV4aXN0aW5nIjp0cnVlfQ==; _production_current_session_hash=fdf0d85b095a71e5e7b6c1bfa6bdd692; _hjSession_2831967=eyJpZCI6IjBiMTU3MGJkLWFiMjktNGVjMi05NWVmLWM5OWQzZGVmYmFjYyIsImNyZWF0ZWQiOjE3MDE0NDMzMzc3MjMsImluU2FtcGxlIjp0cnVlLCJzZXNzaW9uaXplckJldGFFbmFibGVkIjp0cnVlfQ==; _hjAbsoluteSessionInProgress=0; _hjIncludedInSessionSample_2831967=0; _production_adjust_session=eGR1Y1FLdmRwVGI4Rmx6NDhhMFZ0ajA2TG45Q1pyVDUyYVppQ0c2bUVEY2JFZlJkSnVHb3NUVFBQQnpncXd3c2VxVlUyZ21sZVFsbW9QMVpNU1l6T3E0dW10c2o0by96MFFoSHl1R29kc3NqWkU2L1AxUDF4SUdjRkhLcnJSYnJHU0VYVVphd0pBY3RLM1pWK0NQVkpVZXRsT3RGaU4vZVhweklVNmVGZHozWjhnWVgyN3FFdTZPbFFoM0JLd3VNVUhXb3lsK0QvdXIyZ3dQV2p4akkxT1VvMHV5ZUdpcEUrZE16akRUL0U4OFhvSWxScjhHZ01BdWlBdEIvVDMzK2RTTmZzYW1nTFB2NVN4NnpGQ0Q4ZWxxU0xYQlhOblVDWlMzVVdueC9tVy9xZy8rVHc3VTBNc2k4c0U0L3pDVlRKMGphbTZoMHhJTVRKWFRBUWxKWDNSTG9HbmJ0VGFLU2xiajg4TkZDTWtzRlNCZ1ZsVnJ4MnpXVStMd3dodUhWTks1MTRydVVDTzUxajl4QjFqVlJLQT09LS1DUVdnWXpmcVdZelZhRWllazJoYjJBPT0%3D--3b91c11c50bf82e41b80e1a74ddac0f37964235e";
    public static String dingidngUrl = "https://oapi.dingtalk.com/robot/send";
    public static String ACCESS_TOKEN = "a4a1980745c08ff0f6715295fec035d12794457e4d319872c8b48f472b899216";
    public static String SECRET = "SEC937aeea1803ea961688893ab7737c1a6c83683252bafd619e278d53f04bce896";

    public static void main(String[] args) {
        MickoDemo mickoDemo = new MickoDemo();
        String msg = "发送给钉钉的消息";
        mickoDemo.dingdingMsg(msg);
    }

    public void getTable() {
        // 获取主表
        String response = HttpClientUtil.httpRequest(mainTableUrl);

        // 将返回的字符串转为JSON
        JSONObject tableMap = JSONObject.parseObject(response);
        int tableSize = tableMap.getJSONArray("rows").size();
        for (int i = 0; i < tableSize; i++) {
            String channel = tableMap.getJSONArray("rows").getJSONObject(i).getString("channel");
            String channelId = tableMap.getJSONArray("rows").getJSONObject(i).getJSONObject("attr_dependency").getString("channel_id");
            String subUrl = subTableUrl + "&drilldown=channel:" + channelId;
            String subTableResponse = HttpClientUtil.httpRequest(subUrl);

            System.out.println(channel);
            System.out.println(subTableResponse);
        }
    }

    private void excelUtil(String response) {
        // 将返回的JSON字符串转换为Map
        JSONObject tableMap = JSONObject.parseObject(response);
        int tableSize = tableMap.getJSONArray("rows").size();
        for (int i = 0; i < tableSize; i++) {
            String channel = tableMap.getJSONArray("rows").getJSONObject(i).getString("channel");
            String channelId = tableMap.getJSONArray("rows").getJSONObject(i).getJSONObject("attr_dependency").getString("channel_id");
            String subUrl = subTableUrl + "&drilldown=channel:" + channelId;
            String subTableResponse = HttpClientUtil.httpRequest(subUrl);

            System.out.println(channel);
            System.out.println(subTableResponse);
        }
    }

    public void dingdingMsg(String msg) {
        try {
            long timestamp = System.currentTimeMillis();
            String url = new StringBuilder(dingidngUrl)
                    .append("?access_token=").append(ACCESS_TOKEN)
                    .append("&timestamp=").append(timestamp)
                    .append("&sign=").append(getSign(SECRET, timestamp))
                    .toString();
            Map<String, Object> actionCard = new HashMap<>();
            actionCard.put("text", msg);
            Map<String, Object> param = new HashMap<>();
            param.put("actionCard", actionCard);
            param.put("msgtype", "actionCard");
            String responseResult = HttpClientUtil.httpPost(url, JSONObject.toJSONString(param));
            log.info("钉钉发送消息【{}】返回结果：{}", msg, responseResult);
        } catch (Exception e) {
            log.error("钉钉发送消息异常，异常堆栈信息：", e);
        }
    }

    private static String getSign(String tcSecret, long timestamp) throws Exception {
        String stringToSign = timestamp + "\n" + tcSecret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(tcSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return sign;
    }


}


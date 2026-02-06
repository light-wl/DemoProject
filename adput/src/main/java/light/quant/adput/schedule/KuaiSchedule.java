package light.quant.adput.schedule;

import com.alibaba.fastjson.JSONObject;
import light.quant.adput.utils.DingTalkBySecretUtil;
import light.quant.adput.utils.DingTalkUtil;
import light.quant.adput.utils.OkHttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author light
 * @date 2026/2/7
 * @desc 快手广告数据定时任务
 **/
@Component
public class KuaiSchedule {

    @Value("${dingtalk.webhook.url}")
    private String dingTalkWebhookUrl;

    @Value("${kwai.api.url}")
    private String kwaiApiUrl;

    @Value("${kwai.account.id}")
    private String kwaiAccountId;

    @Value("${kwai.client.id}")
    private String kwaiClientId;

    /**
     * 每小时执行一次的定时任务
     * cron 表达式: 0 0 * * * ? 表示每小时的第0分钟执行
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void queryKwaiAdData() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(new Date());
            System.out.println("========== 快手广告数据查询定时任务开始 [" + currentTime + "] ==========");

            // 1. 构建请求头
            Map<String, String> headers = buildHeaders();

            // 2. 构建请求体
            String requestBody = buildRequestBody();

            // 3. 调用快手 API
            String response = OkHttpUtil.sendPOST(kwaiApiUrl, requestBody, headers);

            // 4. 处理响应数据
            if (response != null && !response.isEmpty()) {
                System.out.println("快手 API 响应: " + response);

                // 5. 解析响应数据
                JSONObject responseJson = JSONObject.parseObject(response);

                // 6. 发送到钉钉
                DingTalkBySecretUtil.sendDingMsg(responseJson.toJSONString());
//                sendToDingTalk(responseJson, currentTime);
            } else {
                System.out.println("快手 API 返回空响应");
                sendErrorToDingTalk("快手 API 返回空响应", currentTime);
            }

            System.out.println("========== 快手广告数据查询定时任务结束 ==========");
        } catch (Exception e) {
            System.out.println("快手广告数据查询定时任务异常: " + e.getMessage());
            e.printStackTrace();
            sendErrorToDingTalk("定时任务执行异常: " + e.getMessage(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }

    /**
     * 构建请求头
     */
    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("accept-language", "zh-CN");
        headers.put("accountid", kwaiAccountId);
        headers.put("clientid", kwaiClientId);
        headers.put("content-type", "application/json");
        headers.put("fromcrm", "false");
        headers.put("origin", "https://ads.kwai.com");
        headers.put("referer", "https://ads.kwai.com/?accountId=" + kwaiAccountId);
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36");
        return headers;
    }

    /**
     * 构建请求体
     */
    private String buildRequestBody() {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("pageNum", 1);
        bodyMap.put("pageSize", 10);

        Map<String, Boolean> orderByFields = new HashMap<>();
        orderByFields.put("cost", false);
        bodyMap.put("orderByFields", orderByFields);

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("language", "zh-CN");
        queryParam.put("queryTarget", 6);
        queryParam.put("timeZoneIana", "Asia/Ho_Chi_Minh");

        // 设置时间范围（最近24小时）
        long endTime = System.currentTimeMillis();
        long startTime = endTime - 24 * 60 * 60 * 1000;
        queryParam.put("startTime", startTime);
        queryParam.put("endTime", endTime);
        queryParam.put("granularity", 10);

        Map<String, Object> customReportClientChooseItemParam = new HashMap<>();
        customReportClientChooseItemParam.put("dimensionKey", new String[]{});
        customReportClientChooseItemParam.put("metricKey", new String[]{
            "cost", "costUniquePurchase", "uniquePurchase", "registration",
            "exposure", "click", "costPerRegistration", "registrationRate"
        });
        customReportClientChooseItemParam.put("allQueryItems", new String[]{});
        queryParam.put("customReportClientChooseItemParam", customReportClientChooseItemParam);
        queryParam.put("customReportClientChooseDimEnums", new String[]{});
        queryParam.put("customTargetParams", new String[]{});
        queryParam.put("customReportDetailDimEnumList", new String[]{});

        bodyMap.put("queryParam", queryParam);

        return JSONObject.toJSONString(bodyMap);
    }

    /**
     * 发送成功数据到钉钉
     */
    private void sendToDingTalk(JSONObject responseJson, String currentTime) {
        try {
            StringBuilder content = new StringBuilder();
            content.append("### 快手广告数据查询结果\n\n");
            content.append("**查询时间**: ").append(currentTime).append("\n\n");
            content.append("**响应状态**: ").append(responseJson.getString("code")).append("\n\n");

            // 提取关键数据
            if (responseJson.containsKey("data")) {
                content.append("**数据详情**:\n");
                content.append("```\n");
                content.append(responseJson.getString("data"));
                content.append("\n```\n");
            }

            DingTalkUtil.sendMessage(dingTalkWebhookUrl, "快手广告数据查询", content.toString());
        } catch (Exception e) {
            System.out.println("发送钉钉消息异常: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 发送错误信息到钉钉
     */
    private void sendErrorToDingTalk(String errorMessage, String currentTime) {
        try {
            StringBuilder content = new StringBuilder();
            content.append("### 快手广告数据查询失败\n\n");
            content.append("**查询时间**: ").append(currentTime).append("\n\n");
            content.append("**错误信息**: ").append(errorMessage).append("\n\n");
            content.append("**请及时检查系统日志**");

            DingTalkUtil.sendMessage(dingTalkWebhookUrl, "快手广告数据查询异常", content.toString());
        } catch (Exception e) {
            System.out.println("发送钉钉错误消息异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


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

    @Value("${kwai.ktrace.str}")
    private String kwaiKtraceStr;

    @Value("${kwai.cookie.weblogger_did}")
    private String cookieWebloggerDid;

    @Value("${kwai.cookie.did}")
    private String cookieDid;

    @Value("${kwai.cookie.userId}")
    private String cookieUserId;

    @Value("${kwai.cookie.fw_crm_v}")
    private String cookieFwCrmV;

    @Value("${kwai.cookie.dsp_st}")
    private String cookieDspSt;

    @Value("${kwai.cookie.dsp_ph}")
    private String cookieDspPh;

    @Value("${kwai.cookie.first_session}")
    private String cookieFirstSession;

    @Value("${kwai.cookie.jsessionid}")
    private String cookieJsessionid;

    /**
     * 每小时执行一次的定时任务
     * cron 表达式: 0 0 * * * ? 表示每小时的第0分钟执行
     */
    @Scheduled(cron = "0/5 * * * * ?")
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

                // 6. 提取关键数据并发送到钉钉
                sendKwaiDataToDingTalk(responseJson, currentTime);
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
        headers.put("ktrace-str", kwaiKtraceStr);
        headers.put("origin", "https://ads.kwai.com");
        headers.put("priority", "u=1, i");
        headers.put("referer", "https://ads.kwai.com/?accountId=" + kwaiAccountId);
        headers.put("request-starttime", String.valueOf(System.currentTimeMillis()));
        headers.put("sec-ch-ua", "\"Chromium\";v=\"140\", \"Not=A?Brand\";v=\"24\", \"Google Chrome\";v=\"140\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "\"macOS\"");
        headers.put("sec-fetch-dest", "empty");
        headers.put("sec-fetch-mode", "cors");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36");

        // 添加 Cookie
        String cookies = buildCookies();
        headers.put("Cookie", cookies);

        return headers;
    }

    /**
     * 构建 Cookie 字符串
     * 注意：这些 cookie 值需要定期更新，因为它们会过期
     */
    private String buildCookies() {
        StringBuilder cookieBuilder = new StringBuilder();
        cookieBuilder.append("weblogger_did=").append(cookieWebloggerDid).append("; ");
        cookieBuilder.append("did=").append(cookieDid).append("; ");
        cookieBuilder.append("userId=").append(cookieUserId).append("; ");
        cookieBuilder.append("_fw_crm_v=").append(cookieFwCrmV).append("; ");
        cookieBuilder.append("ksi18n.ad.dsp_st=").append(cookieDspSt).append("; ");
        cookieBuilder.append("ksi18n.ad.dsp_ph=").append(cookieDspPh).append("; ");
        cookieBuilder.append("first_session=").append(cookieFirstSession).append("; ");
        cookieBuilder.append("JSESSIONID=").append(cookieJsessionid);
        return cookieBuilder.toString();
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
     * 发送快手数据到钉钉（提取 cost 和 costUniquePurchase）
     */
    private void sendKwaiDataToDingTalk(JSONObject responseJson, String currentTime) {
        try {
            // 检查响应是否成功
            if (responseJson.getInteger("result") != 1) {
                sendErrorToDingTalk("API 返回错误: " + responseJson.getString("err_msg"), currentTime);
                return;
            }

            // 提取 totalRow 数据
            JSONObject data = responseJson.getJSONObject("data");
            if (data == null) {
                sendErrorToDingTalk("响应数据为空", currentTime);
                return;
            }

            JSONObject totalRow = data.getJSONObject("totalRow");
            if (totalRow == null) {
                sendErrorToDingTalk("totalRow 数据为空", currentTime);
                return;
            }

            // 提取 cost 和 costUniquePurchase，保留前四位
            Object costObj = totalRow.get("cost");
            Object costUniquePurchaseObj = totalRow.get("costUniquePurchase");

            String costStr = formatToFourDigits(costObj);
            String costUniquePurchaseStr = formatToFourDigits(costUniquePurchaseObj);

            // 构建消息内容
            StringBuilder content = new StringBuilder();
            content.append("### 快手广告数据查询结果\n\n");
            content.append("**查询时间**: ").append(currentTime).append("\n\n");
            content.append("**成本 (Cost)**: ").append(costStr).append("\n\n");
            content.append("**独立购买成本 (Cost Unique Purchase)**: ").append(costUniquePurchaseStr).append("\n\n");

            // 调用钉钉发送消息
            DingTalkBySecretUtil.sendDingMsg(content.toString());
        } catch (Exception e) {
            System.out.println("发送钉钉消息异常: " + e.getMessage());
            e.printStackTrace();
            sendErrorToDingTalk("处理数据异常: " + e.getMessage(), currentTime);
        }
    }

    /**
     * 将数字格式化为前四位
     */
    private String formatToFourDigits(Object value) {
        if (value == null) {
            return "0";
        }

        String strValue = value.toString();
        // 如果是科学计数法，先转换为普通数字
        if (strValue.contains("E") || strValue.contains("e")) {
            try {
                double doubleValue = Double.parseDouble(strValue);
                strValue = String.format("%.0f", doubleValue);
            } catch (NumberFormatException e) {
                return strValue;
            }
        }

        // 移除小数点后的部分
        if (strValue.contains(".")) {
            strValue = strValue.substring(0, strValue.indexOf("."));
        }

        // 保留前四位
        if (strValue.length() > 4) {
            return strValue.substring(0, 4);
        }
        return strValue;
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


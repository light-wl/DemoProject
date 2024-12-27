package light.quant.adput.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author light
 * @Date 2024/12/6
 * @Desc
 **/
public class ADLinkConstant {
    /**
     * 调用的URL
     */
    // 1、搜索短剧
    public static final String GET_VIDEO_ID_URL = "https://fbadmin.kwmobi.com/api/video/get_video_list";

    // 2、获取参数 episodesID
    public static final String GET_EPISODES_ID_URL = "https://fbadmin.kwmobi.com/api/link/getEpisodesList";

    // 3、创建投放链接
    public static final String CREATE_AD_LINK_URL = "https://fbadmin.kwmobi.com/api/link/addEdit";
    // 3、根据投放链接ID查询标题
    public static final String SEARCH_AD_LINK_URL = "https://fbadmin.kwmobi.com/api/link/list";

    // 4、创建落地页
    public static final String CREATE_LINK_SHARE_URL = "https://fbadmin.kwmobi.com/api/share/addEdit";
    public static final String LIST_LINK_SHARE_URL = "https://fbadmin.kwmobi.com/api/share/list";


    // 5、复制广告
    public static final String COPY_AD_URL = "https://fbadmin.kwmobi.com/api/common/copy";

    // 6、更新草稿
    public static final String UPDATE_DRAFT_URL = "https://fbadmin.kwmobi.com/api/task/updateDraft";

    public static final String ACCOUNT_ID = "560344103336065";

    /**
     * 常量
     * */
    public static final String VIDEO_ID = "videoId";
    public static final String EPISODES_ID = "episodesId";
    public static final String EPISODES_TITLE = "episodesTitle";
    public static final String LINK_ID = "linkId";
    public static final String LINK_NAME = "linkName";
    public static final String SHARE_ID = "shareId";
    public static final String SHARE_LINK = "shareLink";
    public static final String SHARE_TITLE = "shareTitle";
    public static final String SHARE_DESC = "shareDescribe";
    public static final String ONE_LEVEL_KEY = "oneLevelKey";
    public static final String TWO_LEVEL_KEY = "twoLevelKey";



    /**
     * 1、搜索短剧
     */
    public static String getVideoIdParams(String name) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("page", "1");
        paramsMap.put("page_size", "10");
        paramsMap.put("sex_type", "-1");
        paramsMap.put("video_status", "-1");
        paramsMap.put("is_shelves", "-1");
        paramsMap.put("name", name);
        paramsMap.put("language", "1");
        paramsMap.put("bm_id", "3");
        paramsMap.put("co", "9");
        return JSONObject.toJSONString(paramsMap);
    }

    /**
     * 2、获取参数 episodesID
     */
    public static String getEpisodesIdParams(String videoId) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("video_id", videoId);
        paramsMap.put("platform", "23");
        paramsMap.put("bm_id", "3");
        paramsMap.put("co", "9");
        return JSONObject.toJSONString(paramsMap);
    }

    /**
     * 3、创建投放链接
     */
    public static String getCreateAdLinkParams(String videoId, String episodesId) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("book_id", videoId);
        paramsMap.put("video_id", videoId);
        paramsMap.put("episodes_id", episodesId);
        paramsMap.put("ad_market", "1");
        paramsMap.put("line_client_id", "");
        paramsMap.put("platform", "23");
        paramsMap.put("use_mould", "DISABLED");
        paramsMap.put("chapter_id", "");
        paramsMap.put("system", "0");
        paramsMap.put("version", "1");
        paramsMap.put("link_type", "3");
        paramsMap.put("link_action", "0");
        paramsMap.put("chapter_charge", "0");
        paramsMap.put("adv_template_id", "4");
        paramsMap.put("link_id", "0");
        paramsMap.put("test_type", "0");
        paramsMap.put("bind_pay_mould", "0");
        paramsMap.put("upload_type", "1");
        paramsMap.put("upload_time_type", "1");
        paramsMap.put("upload_time", "168");
        paramsMap.put("limit_money", "0");
        paramsMap.put("order_rule", "");
        paramsMap.put("placement_type", "1");
        paramsMap.put("limit_per_extra_money", "");
        paramsMap.put("ext", "");
        paramsMap.put("code", "");
        paramsMap.put("curTestType", "0");
        paramsMap.put("bm_id", "3");
        paramsMap.put("co", "9");
        return JSONObject.toJSONString(paramsMap);
    }

    /**
     * 3、根据投放链接ID查询标题
     */
    public static String getSearchTitleParams(String linkId) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("link_id", linkId);
        paramsMap.put("system", "");
        paramsMap.put("ad_market", "");
        paramsMap.put("platform", "23");
        paramsMap.put("is_default", "");
        paramsMap.put("line_client_id", "");
        paramsMap.put("promoter_user_id", "");
        paramsMap.put("name", "");
        paramsMap.put("test_type", "");
        paramsMap.put("book_id", "");
        paramsMap.put("page", "1");
        paramsMap.put("page_size", "20");
        paramsMap.put("bm_id", "3");
        paramsMap.put("co", "9");
        return JSONObject.toJSONString(paramsMap);
    }

    /**
     * 4、创建落地页
     */
    public static String getEditParams(String videoId, String episodesId, String episodesTitle) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("book", videoId);
        paramsMap.put("chapter_id", episodesId);
        paramsMap.put("title", episodesTitle);
        paramsMap.put("t_id", "");
        paramsMap.put("tag_id", "");
        paramsMap.put("pixel_id", "2107149852979233");
        paramsMap.put("os", "0");
        paramsMap.put("top_title", "");
        paramsMap.put("model", "4");
        paramsMap.put("package", "23");
        paramsMap.put("copyright", "");
        paramsMap.put("use_adjust", "2");
        paramsMap.put("delay_sec", "0");
        paramsMap.put("bg_color", "5");
        paramsMap.put("env", "1");
        paramsMap.put("content", "");
        paramsMap.put("cta", "https://leshunovel.allreaderwap.com/");
        paramsMap.put("addEditLoading", "false");
        paramsMap.put("platform", "1");
        paramsMap.put("link_id", "70243");
        paramsMap.put("upload_img", "[]");
        paramsMap.put("upload_bottom", "[]");
        paramsMap.put("book_url", "-1");
        paramsMap.put("chapter", "0");
        paramsMap.put("backup", "");
        paramsMap.put("p_size", "1");
        paramsMap.put("focus_on", "0");
        paramsMap.put("report_cv", "0");
        paramsMap.put("btn_content", "Continue Watching");
        paramsMap.put("next_text", "");
        paramsMap.put("prev_text", "");
        paramsMap.put("type", "0");
        paramsMap.put("btn_bg", "#ff4500");
        paramsMap.put("id", "0");
        paramsMap.put("book_platform", "23");
        paramsMap.put("from", "1");
        paramsMap.put("bm_id", "3");
        paramsMap.put("co", "9");
        return JSONObject.toJSONString(paramsMap);
    }

    /**
     * 9、获取落地页列表
     */
    public static String listShareInfo() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("page", "1");
        paramsMap.put("page_size", "20");
        paramsMap.put("from", "1");
        paramsMap.put("package", "");
        paramsMap.put("book_info", "");
        paramsMap.put("uid", "");
        paramsMap.put("bm_id", "1");
        paramsMap.put("co", "9");
        return JSONObject.toJSONString(paramsMap);
    }

    /**
     * 5、复制广告
     */
    public static String getCopyAdParams() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("aid", ACCOUNT_ID); // 账户20
        paramsMap.put("level", "campaign");
        paramsMap.put("ids", "c_173392210249355810284861");  // 我居然闪婚了禁欲大佬
        paramsMap.put("copy_times", "1");
        paramsMap.put("use_post", "0");
        paramsMap.put("bm_id", "1");
        paramsMap.put("co", "9");
        return JSONObject.toJSONString(paramsMap);
    }

    /**
     * 6、更新草稿
     */
    public static String getUpdateDraftParams(String levelName, String levelKey, String level) {
        JsonObject thirdObject = new JsonObject();
        thirdObject.addProperty("name", levelName);
        thirdObject.addProperty("objective", "OUTCOME_SALES");
        thirdObject.addProperty("bid_strategy", "LOWEST_COST_WITHOUT_CAP");
        thirdObject.addProperty("is_skadnetwork_attribution", false);
        thirdObject.addProperty("smart_promotion_type", "GUIDED_CREATION");

        JsonObject secondObject = new JsonObject();
        secondObject.add(levelKey, thirdObject);

        JsonObject jsonMap = new JsonObject();
        jsonMap.addProperty("aid", ACCOUNT_ID);
        jsonMap.addProperty("level", level);
        jsonMap.addProperty("bm_id", 1);
        jsonMap.addProperty("co", "9");
        jsonMap.add("data", secondObject);

        Gson gson = new Gson();
        return gson.toJson(jsonMap);
    }

    /**
     * 更新草稿三级信息
     */
    public static String getUpdateDraftParamsLevelThree(String levelKey, String shareLink) {

        JsonObject eightObject = new JsonObject();
        eightObject.addProperty("link", shareLink);

        JsonObject sevenObject = new JsonObject();
        sevenObject.add("value", eightObject);

        JsonObject sixObject = new JsonObject();
        sixObject.add("call_to_action", sevenObject);

        JsonObject fifthObject = new JsonObject();
        fifthObject.add("video_data", sixObject);

        JsonObject forthObject = new JsonObject();
        forthObject.addProperty("use_page_actor_override", "false");
        forthObject.add("object_story_spec", fifthObject);

        JsonObject thirdObject = new JsonObject();
        thirdObject.add("creative", forthObject);

        JsonObject secondObject = new JsonObject();
        secondObject.add(levelKey, thirdObject);

        JsonObject jsonMap = new JsonObject();
        jsonMap.addProperty("aid", ACCOUNT_ID);
        jsonMap.addProperty("level", "3");
        jsonMap.addProperty("bm_id", 1);
        jsonMap.addProperty("co", "9");
        jsonMap.add("data", secondObject);

        Gson gson = new Gson();
        return gson.toJson(jsonMap);
    }

    public static Map<String, String> getHeader() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("Accept", "application/json, text/plain, */*");
        paramsMap.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        paramsMap.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJpc3MiOiJodHRwczovL2ZiYWRtaW4ua3dtb2JpLmNvbS9hcGkvcmVmcmVzaCIsImlhdCI6MTczMzQ5MDE0NiwiZXhwIjoxNzM0MzU0MTUxLCJuYmYiOjE3MzM5MjIxNTEsImp0aSI6ImdJd05qMHFGSTBiejF3b0UiLCJzdWIiOiI0ODYiLCJwcnYiOiJhOTZhNWI0YzJiN2ExZWFmNTE3Y2Y4ODM5OWQwYmEyN2RlZTBjNzNmIn0.GE0DkaaPkahFfWpdkR87y27KHpgv08uHWWGR4eFQhJQ");
        paramsMap.put("Connection", "keep-alive");
        paramsMap.put("Content-Type", "application/json");
        paramsMap.put("Cookie", "adc-now-co-id=9; tt-show-id=1; adc-cur_platform=23; sidebarStatus=1; " +
                "adc-cur_tiktokAid=7428921062380552208; adc-access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJpc3MiOiJodHRwczovL2ZiYWRtaW4ua3dtb2JpLmNvbS9hcGkvcmVmcmVzaCIsImlhdCI6MTczMzQ5MDE0NiwiZXhwIjoxNzM0MzU0MTUxLCJuYmYiOjE3MzM5MjIxNTEsImp0aSI6ImdJd05qMHFGSTBiejF3b0UiLCJzdWIiOiI0ODYiLCJwcnYiOiJhOTZhNWI0YzJiN2ExZWFmNTE3Y2Y4ODM5OWQwYmEyN2RlZTBjNzNmIn0.GE0DkaaPkahFfWpdkR87y27KHpgv08uHWWGR4eFQhJQ; adc-expire_time=1734354151907; adc-now-co-type=5; adc-cur_aid=1258265265372595; adc-Bid=3; XSRF-TOKEN=eyJpdiI6ImNqdDlUbUk2QzJDQnNseVVIYmwrR0E9PSIsInZhbHVlIjoiSHd5TG1Qak5SY1NrQmMzRlRwQ0wxcnpXdlJ2azFkQ253NGsyUmRkNkZtbmZrTnVwZVhvR2MrUG1VYUcwb2hTeWNNR2huNWMwN2dpNlExQ0Vqbk56ZWVNRVpBRUhNUGw5UXVPSHFVaCtWMitQckpmM3hWcXNKb0owZ1RCcEkzU3kiLCJtYWMiOiJkN2ViNDUwMDA3ZmNmYjM1NGJlN2Q4NGM3NTE5MTkyMmVmYjQxNzIxM2VjZTgwOWY5MzRlM2U5NDM3ZDg1NjUwIiwidGFnIjoiIn0%3D; kw_adc_session=eyJpdiI6ImV4U3M2Q2ZEOW5nV3lTd2Q2NUZGZVE9PSIsInZhbHVlIjoib05YOWpmN2p2WkNMUmtkNFF3c0RUL2hVam44TlhoV3ozR0ZNWjMxOTZZZGpEb1F0N3hnL1pTUFpoWnBwV2x1TXFhbnFNZDQ2UmkrK29QSWRFMjZLQ0hyWVZpeWtlMHpYenk5c3VlcDBWd011NW0wcndFOTNrRzZOWkp4UnhqaGEiLCJtYWMiOiIwMmIyZDQ1MmEzMGVhMzhlMjIzMGJmMWZkMDI3MWVmNGU0ZjFmM2M2YWE3NzQ5OGUyOGIxZmY0YTE5ZGUyNmRmIiwidGFnIjoiIn0%3D");
        paramsMap.put("Origin", "https://fbadmin.kwmobi.com");
//        不同的接口调用，这个地方不同，不要也没问题
//        paramsMap.put("Referer", "https://fbadmin.kwmobi.com/setting/video");
        paramsMap.put("Sec-Fetch-Dest", "empty");
        paramsMap.put("Sec-Fetch-Mode", "cors");
        paramsMap.put("Sec-Fetch-Site", "same-origin");
        paramsMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like " +
                "Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0");
        paramsMap.put("X-XSRF-TOKEN",
                "eyJpdiI6ImNqdDlUbUk2QzJDQnNseVVIYmwrR0E9PSIsInZhbHVlIjoiSHd5TG1Qak5SY1NrQmMzRlRwQ0wxcnpXdlJ2azFkQ253NGsyUmRkNkZtbmZrTnVwZVhvR2MrUG1VYUcwb2hTeWNNR2huNWMwN2dpNlExQ0Vqbk56ZWVNRVpBRUhNUGw5UXVPSHFVaCtWMitQckpmM3hWcXNKb0owZ1RCcEkzU3kiLCJtYWMiOiJkN2ViNDUwMDA3ZmNmYjM1NGJlN2Q4NGM3NTE5MTkyMmVmYjQxNzIxM2VjZTgwOWY5MzRlM2U5NDM3ZDg1NjUwIiwidGFnIjoiIn0=");
        paramsMap.put("sec-ch-ua", "\"Microsoft Edge\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"");
        paramsMap.put("sec-ch-ua-mobile", "?0");
        paramsMap.put("sec-ch-ua-platform", "\"macOS\"");

        return paramsMap;
    }


}

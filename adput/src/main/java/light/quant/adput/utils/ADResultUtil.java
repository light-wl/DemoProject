package light.quant.adput.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：light
 * @date ：2024/12/12 13:52:01
 * @description :
 */
public class ADResultUtil {
    public static String getVideoId(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String videoId = jsonObject.getAsJsonObject("data")
                .getAsJsonObject("video_list")
                .getAsJsonArray("data").get(0).getAsJsonObject()
                .get("id").getAsString();
        return videoId;
    }

    public static String getEpisodesId(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String episodesId = jsonObject.getAsJsonObject("data")
                .getAsJsonArray("list").get(0).getAsJsonObject()
                .get("episodes_id").getAsString();
        return episodesId;
    }

    public static String getEpisodesTitle(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String episodesTitle = jsonObject.getAsJsonObject("data")
                .getAsJsonArray("list").get(0).getAsJsonObject()
                .get("episodes_title").getAsString();
        return episodesTitle;
    }

    public static String getLinkId(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String linkId = jsonObject.getAsJsonObject("data")
                .get("link_id").getAsString();
        return linkId;
    }

    public static String getLinkName(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String linkName = jsonObject.getAsJsonObject("data").
                getAsJsonArray("data").get(0).getAsJsonObject()
                .get("book_name").getAsString();
        return linkName;
    }

    public static String createLinkName(String videoId, String linkId, String name) {
        LocalDate today = LocalDate.now();
        // 创建一个 DateTimeFormatter 对象，指定日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
        // 格式化日期
        String formattedDate = today.format(formatter);
        return String.format("{b%s/l%s/dmj}-%s-%s(com.kwshortvideo.kalostv)",
                videoId, linkId, formattedDate, name);
    }

    public static String getOneLevelKey(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String oneLevelKey = jsonObject.getAsJsonObject("data")
                .getAsJsonObject("list").getAsJsonObject()
                .get("campaign").getAsString();
        return oneLevelKey;
    }

    public static String getTwoLevelKey(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String twoLevelKey = jsonObject.getAsJsonObject("data")
                .getAsJsonObject("list").getAsJsonObject()
                .get("adset").getAsString();
        return twoLevelKey;
    }

    public static String getShareTitle(String htmlStr) {
        int fromIndex = htmlStr.indexOf("// 网站标题");
        int endIndex = htmlStr.indexOf("// 剧情描述");
        String str = htmlStr.substring(fromIndex, endIndex + 10);
        Pattern titlePattern = Pattern.compile("\\$\\(\"\\.bookTitle\"\\)\\.html\\(`(.*?)`\\);.*?// 剧情标题");
        Matcher titleMatcher = titlePattern.matcher(str);
        String title = "";
        if (titleMatcher.find()) {
            title = titleMatcher.group(1);
            System.out.println("标题内容: " + title);
        }
        return title;
    }

    public static String getShareDescribe(String htmlStr) {
        int fromIndex = htmlStr.indexOf("// 网站标题");
        int endIndex = htmlStr.indexOf("// 剧情描述");
        String str = htmlStr.substring(fromIndex, endIndex + 10);
        // 正则表达式匹配剧情内容
        Pattern contentPattern = Pattern.compile("\\$\\(\\\"\\.sec3-txt \\.text\\\"\\)\\.html\\(`([\\s\\S]+?)`\\);");
        Matcher contentMatcher = contentPattern.matcher(str);
        String content = "";
        if (contentMatcher.find()) {
            content = contentMatcher.group(1).replace("&mdash;", "—"); // 替换HTML实体为普通字符
            System.out.println("剧情内容: " + content);
        }
        return content;
    }

    public static String getShareTemp(String htmlStr) {
        int fromIndex = htmlStr.indexOf("// 网站标题");
        int endIndex = htmlStr.indexOf("// 剧情描述");
        String str = htmlStr.substring(fromIndex, endIndex + 10);
        // 正则表达式匹配剧情内容
        Pattern contentPattern = Pattern.compile("\\$\\(\\\"\\.sec3-txt \\.text\\\"\\)\\.html\\(`<p>([\\s\\S]+?)</p>`\\);");
        Matcher contentMatcher = contentPattern.matcher(str);
        String content = "";
        if (contentMatcher.find()) {
            content = contentMatcher.group(1).replace("&mdash;", "—"); // 替换HTML实体为普通字符
            System.out.println("剧情内容: " + content);
        }
        return content;
    }

    public static String getShareId(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        String shareId = jsonObject.getAsJsonObject("data").get("id").getAsString();
        return shareId;
    }

    public static String getShareLink(String jsonString, String shareId) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonArray shareArray = jsonObject.getAsJsonObject("data")
                .getAsJsonArray("data");
        JsonObject share;
        String shareCode = "";
        for (int i = 0; i < shareArray.size(); i++) {
            share = shareArray.get(i).getAsJsonObject();
            String idStr = share.get("id").getAsString();
            if (shareId.equals(idStr)) {
                shareCode = share.get("code").getAsString();
                break;
            }
        }

        // 构造shareURL
        return String.format("https://website.kalostv.com/share/middle/%s?ad_id={{ad.id}}&campaign={{campaign" +
                ".name}}&adgroup={{adset.name}}", shareCode);
    }

    public static void main(String[] args) {
        String link = "https://website.kalostv.com/share/middle/sbh310gx1qiisaaqxxbe5d31";
        String result = OkHttpUtil.sendPOST(link, "", new HashMap<>());
        String title = getShareTitle(result);
        String describe = getShareDescribe(result);
        System.out.println(title);
        System.out.println(describe);
    }
}

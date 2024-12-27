package light.quant.adput.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @Author light
 * @Date 2024/12/8
 * @Desc
 **/
public class OkHttpUtil {
    public void sendGET() {
    }

    public static String sendPOST(String url, String jsonData, Map<String, String> headerMap) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(jsonData.getBytes(), MediaType.parse("application/json")))
                .headers(new Headers.Builder().addAll(Headers.of(headerMap)).build())
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
                return responseBody;
            } else {
                System.out.println(response);
                System.out.println("请求失败，错误码：" + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "失败";
    }
}

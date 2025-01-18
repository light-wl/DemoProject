package light.lesson.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;



/**
 * @author light
 * @date 2023/12/2 11:46
 * @desc
 */
public class HttpClientUtil {
    public static String ACCESS_TOKEN = "a4a1980745c08ff0f6715295fec035d12794457e4d319872c8b48f472b899216";
    public static String cookie = "_hjSessionUser_2831967=eyJpZCI6ImJjN2ExMTFjLTVjYzEtNTFhZC05NjY0LTUzOTMxZjlhODg4MyIsImNyZWF0ZWQiOjE2OTE3MTcyNTYzMTAsImV4aXN0aW5nIjp0cnVlfQ==; _production_current_session_hash=fdf0d85b095a71e5e7b6c1bfa6bdd692; _hjSession_2831967=eyJpZCI6IjBiMTU3MGJkLWFiMjktNGVjMi05NWVmLWM5OWQzZGVmYmFjYyIsImNyZWF0ZWQiOjE3MDE0NDMzMzc3MjMsImluU2FtcGxlIjp0cnVlLCJzZXNzaW9uaXplckJldGFFbmFibGVkIjp0cnVlfQ==; _hjAbsoluteSessionInProgress=0; _hjIncludedInSessionSample_2831967=0; _production_adjust_session=eGR1Y1FLdmRwVGI4Rmx6NDhhMFZ0ajA2TG45Q1pyVDUyYVppQ0c2bUVEY2JFZlJkSnVHb3NUVFBQQnpncXd3c2VxVlUyZ21sZVFsbW9QMVpNU1l6T3E0dW10c2o0by96MFFoSHl1R29kc3NqWkU2L1AxUDF4SUdjRkhLcnJSYnJHU0VYVVphd0pBY3RLM1pWK0NQVkpVZXRsT3RGaU4vZVhweklVNmVGZHozWjhnWVgyN3FFdTZPbFFoM0JLd3VNVUhXb3lsK0QvdXIyZ3dQV2p4akkxT1VvMHV5ZUdpcEUrZE16akRUL0U4OFhvSWxScjhHZ01BdWlBdEIvVDMzK2RTTmZzYW1nTFB2NVN4NnpGQ0Q4ZWxxU0xYQlhOblVDWlMzVVdueC9tVy9xZy8rVHc3VTBNc2k4c0U0L3pDVlRKMGphbTZoMHhJTVRKWFRBUWxKWDNSTG9HbmJ0VGFLU2xiajg4TkZDTWtzRlNCZ1ZsVnJ4MnpXVStMd3dodUhWTks1MTRydVVDTzUxajl4QjFqVlJLQT09LS1DUVdnWXpmcVdZelZhRWllazJoYjJBPT0%3D--3b91c11c50bf82e41b80e1a74ddac0f37964235e";

    public static void main(String[] args) {

    }

    public static void uploadFile(){
        String urlString = "https://oapi.dingtalk.com/media/upload?access_token=" + ACCESS_TOKEN;
        try {
            // 创建URL对象
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法
            connection.setRequestMethod("POST");
            // 启用输入输出流
            connection.setDoOutput(true);
            // 设置cookie
            connection.setRequestProperty("Cookie", cookie);
            // 设置请求头
            connection.setRequestProperty("Content-Type", "multipart/form-data");
            // 获取输出流并写入请求数据
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
//                outputStream.write(param.getBytes(StandardCharsets.UTF_8));
            }

            // 获取响应代码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // 关闭连接
            connection.disconnect();
            // 打印响应内容
            System.out.println("Response Content: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String httpPost(String urlString, String param) {
        try {
            // 创建URL对象
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法
            connection.setRequestMethod("POST");
            // 启用输入输出流
            connection.setDoOutput(true);

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json");
            // 获取输出流并写入请求数据
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(param.getBytes(StandardCharsets.UTF_8));
            }

            // 获取响应代码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // 关闭连接
            connection.disconnect();
            // 打印响应内容
            System.out.println("Response Content: " + response.toString());
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String httpRequest(String urlString) {
        try {
            // 创建URL对象
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法
            connection.setRequestMethod("GET");
            // 设置cookie
            connection.setRequestProperty("Cookie", cookie);

            // 获取响应代码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // 关闭连接
            connection.disconnect();
            // 打印响应内容
            System.out.println("Response Content: " + response.toString());
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}


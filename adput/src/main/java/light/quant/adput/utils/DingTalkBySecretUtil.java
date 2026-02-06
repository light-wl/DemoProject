package light.quant.adput.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author light
 * @date 2026/2/7
 * @desc
 **/
public class DingTalkBySecretUtil {

    // 钉钉参数
    public static final String WEBHOOK_URL = "https://oapi.dingtalk.com/robot/send";
    public static final String ACCESS_TOKEN = "230e691ba0f3afdf6357ec18e7113cd75f7483721ca249e0544c89c142dda77e";
    public static final String SECRET = "SEC1e17b3e803266f6d7969a9e89e4121272ca1cac56fe426ff6aea9dd882a8b861";

    public static void sendDingMsg(String msg) {
        try {
            Long timestamp = System.currentTimeMillis();
            System.out.println(timestamp);
            String secret = SECRET;
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
            System.out.println(sign);

            //sign字段和timestamp字段必须拼接到请求URL上，否则会出现 310000 的错误信息
            String serverUrl = String.format("%s?timestamp=%s&sign=%s", WEBHOOK_URL, timestamp, sign);
            DingTalkClient client = new DefaultDingTalkClient(serverUrl);
            OapiRobotSendRequest req = new OapiRobotSendRequest();
            /**
             * 发送文本消息
             */
            //定义文本内容
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(msg);
            //定义 @ 对象
//            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//            at.setAtUserIds(Arrays.asList(USER_ID));
            //设置消息类型
            req.setMsgtype("text");
            req.setText(text);
//            req.setAt(at);
            OapiRobotSendResponse rsp = client.execute(req, ACCESS_TOKEN);
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}

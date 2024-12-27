package light.quant.adput.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

/**
 * 使用HttpClient实现
 */
public class HttpClientUtil {

    /**
     * 编码格式。发送编码格式统一用UTF-8
     */
    private static final String ENCODING = "UTF-8";

    /**
     * 设置连接主机超时时间，单位毫秒。
     */
    private static final Integer CONNECT_TIMEOUT = 6000;

    /**
     * 设置连接请求超时时间，单位毫秒。
     */
    private static final Integer CONNECTION_REQUEST_TIMEOUT = 6000;

    /**
     * 请求获取数据的超时时间(即响应时间)，单位毫秒。
     */
    private static final Integer SOCKET_TIMEOUT = 6000;

    /**
     * 发送get请求
     *
     * @param url 链接
     * @return 执行结果
     */
    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    /**
     * 发送get请求
     *
     * @param url    链接
     * @param params 请求参数
     * @return 执行结果
     */
    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, null, params);
    }

    /**
     * 发送get请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  请求参数
     * @return 执行结果
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, String> params) {
        // 创建client对象
        CloseableHttpClient client = new DefaultSSLUtils();
        // 创建response对象
        CloseableHttpResponse response = null;
        // 响应结果
        String result = null;
        try {
            // 创建访问地址，处理请求参数
            URIBuilder uriBuilder = handleParam(url, params);
            // 创建http对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 处理config参数
            handleConfig(httpGet);
            // 处理请求头
            handleHeader(headers, httpGet);
            // 执行请求
            response = client.execute(httpGet);
            // 响应结果
            result = EntityUtils.toString(response.getEntity(), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            release(client, response);
        }
        return result;
    }

    /**
     * 发送post请求
     *
     * @param url     链接
     * @param headers 请求头
     * @return 执行结果
     */
    public static String doPost(String url, Map<String, String> headers) {
        return doPost(url, headers, null, null);
    }

    /**
     * 发送post请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  请求参数
     * @return 执行结果
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> params) {
        return doPost(url, headers, params, null);
    }

    /**
     * 发送post请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param body    请求体
     * @return 执行结果
     */
    public static String doPost(String url, Map<String, String> headers, String body) {
        return doPost(url, headers, null, body);
    }

    /**
     * 发送post请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  请求参数
     * @param body    请求体
     * @return 执行结果
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> params, String body) {
        // 创建client对象
        CloseableHttpClient client = new DefaultSSLUtils();
        // 创建response对象
        CloseableHttpResponse response = null;
        // 响应结果
        String result = null;
        try {
            // 创建访问地址，处理请求参数
            URIBuilder uriBuilder = handleParam(url, params);
            // 创建http对象
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            // 处理config参数
            handleConfig(httpPost);
            // 处理请求头
            handleHeader(headers, httpPost);
            // 处理请求体
            handleBody(body, httpPost);
            // 执行请求
            response = client.execute(httpPost);
            // 响应结果
            result = EntityUtils.toString(response.getEntity(), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(client, response);
        }
        return result;
    }

    /**
     * 发送put请求
     *
     * @param url     链接
     * @param headers 请求头
     * @return 执行结果
     */
    public static String doPut(String url, Map<String, String> headers) {
        return doPut(url, headers, null, null);
    }

    /**
     * 发送put请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  请求参数
     * @return 执行结果
     */
    public static String doPut(String url, Map<String, String> headers, Map<String, String> params) {
        return doPut(url, headers, params, null);
    }

    /**
     * 发送put请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param body    请求体
     * @return 执行结果
     */
    public static String doPut(String url, Map<String, String> headers, String body) {
        return doPut(url, headers, null, body);
    }

    /**
     * 发送put请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  请求参数
     * @return 执行结果
     */
    public static String doPut(String url, Map<String, String> headers, Map<String, String> params, String body) {
        // 创建client对象
        CloseableHttpClient client = new DefaultSSLUtils();
        // 创建response对象
        CloseableHttpResponse response = null;
        // 响应结果
        String result = null;
        try {
            // 创建访问地址，处理请求参数
            URIBuilder uriBuilder = handleParam(url, params);
            // 创建http对象
            HttpPut httpPut = new HttpPut(uriBuilder.build());
            // 处理config参数
            handleConfig(httpPut);
            // 处理请求头
            handleHeader(headers, httpPut);
            // 处理请求体
            handleBody(body, httpPut);
            // 执行请求
            response = client.execute(httpPut);
            // 响应对象
            result = EntityUtils.toString(response.getEntity(), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(client, response);
        }
        return result;
    }

    /**
     * 发送delete请求
     *
     * @param url     链接
     * @param headers 请求头
     * @return 执行结果
     */
    public static String doDelete(String url, Map<String, String> headers) {
        return doDelete(url, headers, null, null);
    }

    /**
     * 发送delete请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  请求参数
     * @return 执行结果
     */
    public static String doDelete(String url, Map<String, String> headers, Map<String, String> params) {
        return doDelete(url, headers, params, null);
    }

    /**
     * 发送delete请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param body    请求体
     * @return 执行结果
     */
    public static String doDelete(String url, Map<String, String> headers, String body) {
        return doDelete(url, headers, null, body);
    }

    /**
     * 发送delete请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  请求参数
     * @return 执行结果
     */
    public static String doDelete(String url, Map<String, String> headers, Map<String, String> params, String body) {
        // 创建client对象
        CloseableHttpClient client = new DefaultSSLUtils();
        // 创建response对象
        CloseableHttpResponse response = null;
        // 响应结果
        String result = null;
        try {
            // 创建访问地址，处理请求参数
            URIBuilder uriBuilder = handleParam(url, params);
            // 创建http对象
            HttpDelete httpDelete = new HttpDelete(uriBuilder.build());
            // 处理config参数
            handleConfig(httpDelete);
            // 处理请求头
            handleHeader(headers, httpDelete);
            // 处理请求体
            handleBody(body, httpDelete);
            // 执行请求
            response = client.execute(httpDelete);
            // 响应对象
            result = EntityUtils.toString(response.getEntity(), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(client, response);
        }
        return result;
    }

    /**
     * 发送Form表单（请求方式：POST）
     *
     * @param url       链接
     * @param headers   请求头
     * @param formParam Form表单参数
     * @return 执行结果
     */
    public static String sendForm(String url, Map<String, String> headers, Map<String, String> formParam) {
        return sendForm(url, headers, formParam, null, null);
    }

    /**
     * 发送Form表单
     *
     * @param url      链接
     * @param headers  请求头
     * @param fileName 文件参数名
     * @param file     文件对象
     * @return 执行结果
     */
    public static String sendForm(String url, Map<String, String> headers, String fileName, File file) {
        return sendForm(url, headers, null, fileName, file);
    }

    /**
     * 发送Form表单
     *
     * @param url       链接
     * @param headers   请求头
     * @param formParam Form表单参数
     * @param fileKey   文件参数名
     * @param file      文件对象
     * @return 执行结果
     */
    public static String sendForm(String url, Map<String, String> headers, Map<String, String> formParam, String fileKey, File file) {
        // 创建client对象
        CloseableHttpClient client = new DefaultSSLUtils();
        // 创建response对象
        CloseableHttpResponse response = null;
        // 响应结果
        String result = null;
        try {
            // 创建http对象
            HttpPost httpPost = new HttpPost(url);
            // 处理config参数
            handleConfig(httpPost);
            // 处理请求头
            handleHeader(headers, httpPost);
            // 处理Form表单参数
            handleFormParam(formParam, fileKey, file, httpPost);
            // 执行请求
            response = client.execute(httpPost);
            // 响应对象
            result = EntityUtils.toString(response.getEntity(), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(client, response);
        }
        return result;
    }

    /**
     * 处理请求参数
     *
     * @param url    链接
     * @param params 请求参数
     * @return 执行结果
     */
    private static URIBuilder handleParam(String url, Map<String, String> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return uriBuilder;
    }

    /**
     * 处理config设置
     *
     * @param httpMethod 方法对象
     */
    private static void handleConfig(HttpRequestBase httpMethod) {
        if (httpMethod != null) {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
            httpMethod.setConfig(requestConfig);
        }
    }

    /**
     * 处理请求头
     *
     * @param params     请求参数
     * @param httpMethod 方法对象
     */
    private static void handleHeader(Map<String, String> params, HttpRequestBase httpMethod) {
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 处理请求体
     *
     * @param body       请求体
     * @param httpEntity 方法对象
     */
    private static void handleBody(String body, HttpEntityEnclosingRequestBase httpEntity) {
        if (body != null) {
            httpEntity.setEntity(new StringEntity(body, ENCODING));
        }
    }

    /**
     * 处理Form表单参数
     *
     * @param formParam Form表单参数
     * @param fileKey   文件参数名
     * @param file      文件对象
     */
    private static void handleFormParam(Map<String, String> formParam, String fileKey, File file, HttpPost httpPost) {
        // 处理文件参数
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.RFC6532); //以浏览器兼容方式 防止文字乱码
        if (fileKey != null && file != null) {
            builder.addBinaryBody(fileKey, file);
        }
        // 处理其他表单参数
        if (formParam != null && formParam.size() > 0) {
            Set<Map.Entry<String, String>> entries = formParam.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.addPart(key, new StringBody(value, ContentType.create("text/plain", "UTF-8")));
            }
        }
        // 设置编码方式
        builder.setCharset(Charset.forName("utf-8"));
        httpPost.setEntity(builder.build());
    }

    /**
     * 释放资源
     */
    private static void release(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    if (closeable != null) {
                        closeable.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 摘抄自：org.apache.http.client.methods.HttpPut
     **/
    static class HttpDelete extends HttpEntityEnclosingRequestBase {

        public final static String METHOD_NAME = "DELETE";

        public HttpDelete() {
            super();
        }

        public HttpDelete(final URI uri) {
            super();
            setURI(uri);
        }

        /**
         * @throws IllegalArgumentException if the uri is invalid.
         */
        public HttpDelete(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }

    }
}

/**
 * 使用https访问方式
 */
class DefaultSSLUtils extends DefaultHttpClient {
    DefaultSSLUtils() {
        super();
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = this.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

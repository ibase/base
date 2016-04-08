package base.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by base on 2016/4/8.
 */
public class HttpClientHelper {

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(10000).setConnectTimeout(10000).build();

    /**
     *
     * @param url 待获取的地址
     * @param charset 页面编码
     * @return 网页源代码
     * @throws java.io.IOException
     */
    public static String getHTML(String url,String charset) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        // ContentType contentType = ContentType.get(entity);
        String html = EntityUtils.toString(entity, charset);
        httpClient.close();
        // httpClient.getConnectionManager().shutdown();
        return html;
    }

    /**
     *
     * @param url 待获取的地址
     * @param charset 页面编码
     * @param connectTimeout 请求超时时间ms
     * @param socketTimeout 响应超时时间ms
     * @return 网页源代码
     * @throws java.io.IOException
     */
    public static String getHTML(String url,String charset,int connectTimeout,int socketTimeout) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);

        requestConfig = RequestConfig.custom()
                .setSocketTimeout(connectTimeout).setConnectTimeout(socketTimeout).build();
        request.setConfig(requestConfig);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity, charset);
        httpClient.close();
        return html;
    }
}

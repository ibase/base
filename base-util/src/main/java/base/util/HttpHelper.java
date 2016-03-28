package base.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by base on 2016/3/28.
 */
public class HttpHelper {

    /**
     * 获取Http请求返回的信息
     * @param requestUrl
     * @return
     * @throws Exception
     */
    public static String getInfoByRequestUrl(String requestUrl) throws Exception {

        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        // 对应的字符编码转换
        Reader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);

        StringBuffer sb = new StringBuffer();
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            sb.append(temp);
        }
        reader.close();
        connection.disconnect();

        return sb.toString();
    }
}

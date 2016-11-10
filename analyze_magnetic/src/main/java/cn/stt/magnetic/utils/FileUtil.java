package cn.stt.magnetic.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016-11-09.
 */
public class FileUtil {

    //获取文件大小
    public static long getFileSize(String urlStr, String cookie) {
        try {
            long lenght = 0;
            URL mUrl = new URL(urlStr);
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            conn.setDoOutput(true);
            conn.setRequestProperty("Cookie", "FTN5K=" + cookie);
            conn.setRequestProperty("Accept-Language", "zh-cn");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/4.0(compatible; MSIE 6.0; Windows NT 5.0; MyIE2; .NET CLR 1.1.4322)");
            conn.connect();
            int responseCode = conn.getResponseCode();
            // 判断请求是否成功处理
            if (responseCode == 200) {
                lenght = conn.getContentLength();
            }

            conn.disconnect();
            return lenght;
        } catch (IOException e) {

            System.out.println("发送GET请求出现异常！" + e + urlStr);
            e.printStackTrace();
        }

        return 0;
    }
}

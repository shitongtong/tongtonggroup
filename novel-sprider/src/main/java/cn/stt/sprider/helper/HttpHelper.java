//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.helper;

import org.apache.http.impl.client.CloseableHttpClient;
import cn.stt.sprider.constants.GlobalConfig;
import cn.stt.sprider.utils.HttpUtil;

public class HttpHelper {
    public HttpHelper() {
    }

    public static String getContent(CloseableHttpClient client, String url, String charset) throws Exception {
        return HttpUtil.getContent(client, url, charset, GlobalConfig.USER_AGENT.getValue());
    }
}

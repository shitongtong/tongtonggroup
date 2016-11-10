package cn.stt.utils;

import cn.stt.po.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stt on 2016/11/10.
 */
public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";   //文本
    public static final String MESSAGE_IMAGE = "image"; //图片
    public static final String MESSAGE_voice = "voice";  //语音
    public static final String MESSAGE_video = "video";  //视频
    public static final String MESSAGE_link = "link";   //链接
    public static final String MESSAGE_TEXT = "location";   //地理位置
    public static final String MESSAGE_TEXT = "event";  //事件
    public static final String MESSAGE_TEXT = "subscribe";  //关注事件
    public static final String MESSAGE_TEXT = "unsubscribe";    //取消关注事件
    public static final String MESSAGE_TEXT = "click";  //点击事件
    public static final String MESSAGE_TEXT = "view";

    /**
     * xml转为map集合
     * @param request
     * @return
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request){
        Map<String,String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;
        try {
            ins = request.getInputStream();
            Document doc = reader.read(ins);
            Element rootElement = doc.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements){
                map.put(element.getName(),element.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 将文本消息转换为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",textMessage.getClass());
        String xml = xStream.toXML(textMessage);
        return xml;
    }

    /**
     * 主菜单
     * @return
     */
    public static String menuText(){
        StringBuilder sb = new StringBuilder();
        sb.append("此乃关注回复，谢谢合作！！！\n\n");
        return sb.toString();
    }
}

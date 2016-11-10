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

    public static final String MESSAGE_TEXT = "text";   //�ı�
    public static final String MESSAGE_IMAGE = "image"; //ͼƬ
    public static final String MESSAGE_voice = "voice";  //����
    public static final String MESSAGE_video = "video";  //��Ƶ
    public static final String MESSAGE_link = "link";   //����
    public static final String MESSAGE_TEXT = "location";   //����λ��
    public static final String MESSAGE_TEXT = "event";  //�¼�
    public static final String MESSAGE_TEXT = "subscribe";  //��ע�¼�
    public static final String MESSAGE_TEXT = "unsubscribe";    //ȡ����ע�¼�
    public static final String MESSAGE_TEXT = "click";  //����¼�
    public static final String MESSAGE_TEXT = "view";

    /**
     * xmlתΪmap����
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
     * ���ı���Ϣת��Ϊxml
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
     * ���˵�
     * @return
     */
    public static String menuText(){
        StringBuilder sb = new StringBuilder();
        sb.append("���˹�ע�ظ���лл����������\n\n");
        return sb.toString();
    }
}

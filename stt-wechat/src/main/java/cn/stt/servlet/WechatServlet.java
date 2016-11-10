package cn.stt.servlet;

import cn.stt.po.TextMessage;
import cn.stt.utils.CheckUtil;
import cn.stt.utils.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by stt on 2016/11/10.
 */
public class WechatServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String signature = req.getParameter("signature");//΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
        String timestamp = req.getParameter("timestamp");//ʱ���
        String nonce = req.getParameter("nonce");//�����
        String echostr = req.getParameter("echostr");//����ַ���

        PrintWriter out = resp.getWriter();
        if (CheckUtil.checkSignature(signature,timestamp,nonce)){
            out.print(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String, String> map = MessageUtil.xmlToMap(req);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
//        String createTime = map.get("CreateTime");
        String msgType = map.get("MsgType");
        String content = map.get("Content");
//        String msgId = map.get("MsgId");

        String message = null;
        if ("text".equals(msgType)){
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(toUserName);
            textMessage.setFromUserName(fromUserName);
            textMessage.setMsgType(msgType);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setContent("�㷢�͵���Ϣ�ǣ�" + content);
            message = MessageUtil.textMessageToXml(textMessage);

            if ("1".equals(content)){
                //TODO
            }else if ("2".equals(content)){
                //TODO
            }

        }else if("�¼�".equals(msgType)){
            String event = map.get("Event");
            if ("��ע�¼�".equals(event)){
                TextMessage textMessage = new TextMessage();
                message = MessageUtil.textMessageToXml(textMessage);
            }
        }
        PrintWriter out = resp.getWriter();
        out.print(message);
        out.close();
    }


}

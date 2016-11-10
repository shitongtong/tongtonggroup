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

        String signature = req.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = req.getParameter("timestamp");//时间戳
        String nonce = req.getParameter("nonce");//随机数
        String echostr = req.getParameter("echostr");//随机字符串

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
            textMessage.setContent("你发送的消息是：" + content);
            message = MessageUtil.textMessageToXml(textMessage);

            if ("1".equals(content)){
                //TODO
            }else if ("2".equals(content)){
                //TODO
            }

        }else if("事件".equals(msgType)){
            String event = map.get("Event");
            if ("关注事件".equals(event)){
                TextMessage textMessage = new TextMessage();
                message = MessageUtil.textMessageToXml(textMessage);
            }
        }
        PrintWriter out = resp.getWriter();
        out.print(message);
        out.close();
    }


}

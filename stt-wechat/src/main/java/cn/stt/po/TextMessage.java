package cn.stt.po;

/**
 * Created by stt on 2016/11/10.
 */
public class TextMessage {
    private String ToUserName;  //	������΢�ź�
    private String FromUserName;    //	���ͷ��ʺţ�һ��OpenID��
    private Long CreateTime;  //	��Ϣ����ʱ�� �����ͣ�
    private String MsgType; //	text
    private String Content; //	�ı���Ϣ����
    private Long MsgId;   //	��Ϣid��64λ����

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }
}

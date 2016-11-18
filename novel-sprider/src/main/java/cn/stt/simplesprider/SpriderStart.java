package cn.stt.simplesprider;

import cn.stt.sprider.constants.GlobalConfig;
import cn.stt.sprider.entity.NovelEntity;
import cn.stt.sprider.enums.CategoryGradeEnum;
import cn.stt.sprider.enums.RepairParamEnum;
import cn.stt.sprider.enums.UserAgentEnum;
import cn.stt.sprider.exception.BaseException;
import cn.stt.sprider.helper.ParseHelper;
import cn.stt.sprider.model.Rule;
import cn.stt.sprider.utils.HttpUtil;
import cn.stt.sprider.utils.PatternUtil;
import cn.stt.sprider.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2016-11-17.
 */
public class SpriderStart {

    private static Logger logger = LoggerFactory.getLogger(SpriderStart.class);

    public static void main(String[] args) {
        //规则文件路径
        String ruleFilePath = "D:\\project_git\\tongtonggroup\\novel-sprider\\src\\main\\resources\\rules\\piaotian.net.xml";
        //解析采集规则文件
        File ruleFile = new File(ruleFilePath);
        SAXReader saxReader = new SAXReader();
        Map<String, Rule> ruleMap = new HashMap<>();
        try {
            //获取xml文档对象
            Document document = saxReader.read(ruleFile);
            //获取根节点，即<RuleConfigInfo></RuleConfigInfo>
            Element rootElement = document.getRootElement();
            //获取根节点下所有子节点<GetSiteName></GetSiteName>
            List<Element> elements = rootElement.elements();
            //迭代
            Iterator<Element> elementIterator = elements.iterator();
            while (elementIterator.hasNext()) {
                //将节点名称转化为属性并保存其值
                Rule rule = new Rule();

                Element element = elementIterator.next();
                Iterator nodeIterator = element.nodeIterator();
                while (nodeIterator.hasNext()) {
                    Node node = (Node) nodeIterator.next();
                    if (StringUtils.equals(node.getName(), "Pattern")) {
                        rule.setPattern(node.getStringValue());
                    } else if (StringUtils.equals(node.getName(), "FilterPattern")) {
                        rule.setFilterPattern(node.getStringValue());
                    } else if (StringUtils.equals(node.getName(), "RegexName")) {
                        rule.setRegexName(node.getStringValue());
                    } else if (StringUtils.equals(node.getName(), "Method")) {
                        rule.setMethod(node.getStringValue());
                    } else if (StringUtils.equals(node.getName(), "Options")) {
                        rule.setOptions(node.getStringValue());
                    }
                }

                ruleMap.put(element.getName(), rule);
            }


            //小说列表url（换行符分隔）
            String allUrl = ruleMap.get("NovelListUrl").getPattern();
            String[] urls = allUrl.split("\n");
            int length = urls.length;

            CloseableHttpClient client = HttpUtil.buildClient(60 * 1000);
            List<String> novelNumlist = new ArrayList();

            for (int i = 0; i < length; i++) {
                String url = urls[i];
                //对列表url发出请求
//                url = "http://www.piaotian.net/quanben/index.html";
//                url = "http://www.piaotian.net/booktoplastupdate/0/4.html";
//                url = "http://www.piaotian.net/booksort/0/1.html";
                String content = HttpUtil.getContent(client, url, "GBK", UserAgentEnum.BAIDU.getValue());
//                logger.debug("content:{}",content);
                //小说书号
                List<String> novelKeyList = PatternUtil.getValues(content, ruleMap.get("NovelList_GetNovelKey"));
                novelNumlist.addAll(novelKeyList);
            }
            client.close();

            CloseableHttpClient client1 = HttpUtil.buildClient(60 * 1000);
            ListIterator<String> listIterator = novelNumlist.listIterator();
            while (listIterator.hasNext()) {
                String novelNum = listIterator.next();
                String assignURL = ruleMap.get("NovelUrl").getPattern();
                logger.debug("assignURL:{}",assignURL);
                if(StringUtils.isNotBlank(assignURL)) {
                    String infoURL = ParseHelper.getAssignURL(assignURL, novelNum);
                    logger.debug("infoURL:{}",infoURL);
                    //小说阅读页面
                    String content = HttpUtil.getContent(client1, infoURL, "GBK", UserAgentEnum.BAIDU.getValue());
//                    logger.debug("novelNum:{},content:{}",novelNum,content);
                    //书名
                    String novelName = StringUtils.trimToEmpty(PatternUtil.getValue(content, ruleMap.get("NovelName")));
                    logger.debug("novelName:{}",novelName);
                    NovelEntity novel = new NovelEntity();
                    novel.setNovelName(novelName);
                    //作者
                    String author = ParseHelper.get(content, ruleMap.get("NovelAuthor"));
                    logger.debug("author:{}",author);
                    novel.setAuthor(author);
                    Integer cat = 0;
                    //分类：大类
                    String topCat = ParseHelper.get(content, ruleMap.get("LagerSort"));
//                    cat = ParseHelper.getCategory(topCat, CategoryGradeEnum.TOP);
//                    novel.setTopCategory(cat);
                    logger.debug("大类：topCat:{},cat:{}",topCat,cat);

                    //分类：小类
                    String smallSort = ParseHelper.get(content, ruleMap.get("SmallSort"));
//                    cat = ParseHelper.getCategory(smallSort, CategoryGradeEnum.SUB);
//                    novel.setSubCategory(cat);
                    logger.debug("小类：smallSort:{},cat:{}",smallSort,cat);

                    //简介
                    String novelIntro = ParseHelper.get(content, ruleMap.get("NovelIntro"));
                    if(StringUtil.isNotBlank(novelIntro)) {
                        novelIntro = StringUtil.replaceHtml(novelIntro);
                        novelIntro = StringUtil.removeBlankLine(novelIntro);
                    }
                    novel.setIntro(StringUtil.isBlank(novelIntro)?"":novelIntro);
                    logger.debug("简介：novelIntro:{}",novelIntro);

                    //关键词
                    String novelKeyword = ParseHelper.get(content, ruleMap.get("NovelKeyword"));
                    novel.setKeywords(StringUtil.isBlank(novelKeyword)?"":novelKeyword);
                    logger.debug("关键词：novelKeyword:{}",novelKeyword);

                    //写作进度
                    String novelDegree = ParseHelper.get(content, ruleMap.get("NovelDegree"));
//                    String fullFlagStr = GlobalConfig.collect.getString("full_flag", "已完结");
                    String fullFlagStr = "已完结";
                    boolean fullFlag = fullFlagStr.equals(novelDegree);
                    novel.setFullFlag(fullFlag);
                    logger.debug("写作进度：fullFlag:{}",fullFlag);

                    //小说封面地址，如：http://www.piaotian.net/files/article/image/7/7689/7689s.jpg
                    String novelCover = PatternUtil.getValue(content, ruleMap.get("NovelCover"));
                    if(StringUtil.isBlank(novelCover)) {
                        String novelDefaultCoverUrl = (ruleMap.get("NovelDefaultCoverUrl")).getPattern();
                        if(novelDefaultCoverUrl != null && !novelDefaultCoverUrl.isEmpty()) {
                            novelCover = novelDefaultCoverUrl;
                        }
                    }
                    logger.debug("小说封面：novelCover:{}",novelCover);

                    String novelPubKey = ParseHelper.get(content, ruleMap.get("NovelInfo_GetNovelPubKey"));
                    logger.debug("小说章节列表url：novelPubKey:{}",novelPubKey);
                    if(StringUtil.isBlank(novelPubKey)) {
                        novelPubKey = (ruleMap.get("PubIndexUrl")).getPattern();
                        if(StringUtil.isBlank(novelPubKey)) {
                            throw new BaseException("无法从页面获取目录页地址， 需要在规则中PubIndexUrl项指定目录页地址");
                        }
                        novelPubKey = ParseHelper.getAssignURL(novelPubKey, novelNum);
                    }
                    logger.debug("小说章节列表url：novelPubKey:{}",novelPubKey);
                }
                break;
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        int a = 0;
        a |= 2;
        System.out.println(a);
    }
}

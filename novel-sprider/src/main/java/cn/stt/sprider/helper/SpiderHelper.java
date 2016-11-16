//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.helper;

import org.apache.commons.cli.CommandLine;
import org.apache.http.impl.client.CloseableHttpClient;
import cn.stt.sprider.constants.Constants;
import cn.stt.sprider.constants.GlobalConfig;
import cn.stt.sprider.enums.ParamEnum;
import cn.stt.sprider.exception.CmdParamException;
import cn.stt.sprider.model.CollectParam;
import cn.stt.sprider.model.Rule;
import cn.stt.sprider.utils.HttpUtil;
import cn.stt.sprider.utils.PatternUtil;
import cn.stt.sprider.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SpiderHelper {
    private static final String ASSIGN_EVERY_SEPARATOR = ",";
    private static final String ASSIGN_SECTIOIN_SEPARATOR = "-";
    private static final String RULE_LINE_SEPARATOR = "\n";

    public SpiderHelper() {
    }

    public static List<String> getArticleNo(CollectParam cpm) throws Exception {
        CloseableHttpClient client = HttpUtil.buildClient(Constants.TEST_TIMEOUT.intValue());
        List list = null;

        try {
            list = getArticleNo((CommandLine)null, cpm, client);
        } catch (Exception var7) {
            throw new Exception(var7.getMessage());
        } finally {
            client.close();
        }

        return list;
    }

    public static List<String> getArticleNo(CommandLine cmd, CollectParam cpm) throws Exception {
        int timeOut = GlobalConfig.site.getInt("connection_timeout", 60);
        CloseableHttpClient client = HttpUtil.buildClient(timeOut * 1000);
        List list = getArticleNo(cmd, cpm, client);
        client.close();
        return list;
    }

    public static List<String> getArticleNo(CommandLine cmd, CollectParam cpm, CloseableHttpClient client) throws Exception {
        ArrayList list = new ArrayList();
        String value;
        String[] e;
        int end;
        if(cmd != null && !cmd.hasOption(ParamEnum.COLLECT_All.getName()) && !cmd.hasOption(ParamEnum.REPAIR_ALL.getName()) && !cmd.hasOption(ParamEnum.IMPORT.getName())) {
            value = cmd.getOptionValue(ParamEnum.COLLECT_ASSIGN.getName());
            if(StringUtil.isBlank(value)) {
                value = cmd.getOptionValue(ParamEnum.REPAIR_ASSIGN.getName());
            }

            if(value.indexOf(",") > 0) {
                e = value.split(",");
                list.addAll(Arrays.asList(e));
            } else if(value.indexOf("-") > 0) {
                try {
                    String var13 = value.split("-")[0];
                    if(!Pattern.matches("\\d*", var13)) {
                        var13 = "0";
                    }

                    int var14 = Integer.parseInt(var13);
                    String var15 = value.split("-")[1];
                    Pattern.matches("\\d*", var15);
                    end = Integer.parseInt(var15);
                    int var16 = Math.min(var14, end);
                    int var17 = Math.max(var14, end);

                    for(int i = var16; i <= var17; ++i) {
                        list.add(String.valueOf(i));
                    }
                } catch (CmdParamException var12) {
                    throw new CmdParamException("命令行参数错误！");
                }
            } else {
                list.add(value);
            }
        } else {
            value = ((Rule)cpm.getRuleMap().get("NovelListUrl")).getPattern();
            e = value.split("\n");
            String[] s = e;
            end = e.length;

            for(int endStr = 0; endStr < end; ++endStr) {
                String start = s[endStr];
                String e1 = HttpHelper.getContent(client, start.trim(), cpm.getRemoteSite().getCharset());
                list.addAll(PatternUtil.getValues(e1, (Rule)cpm.getRuleMap().get("NovelList_GetNovelKey")));
            }
        }

        return list;
    }
}

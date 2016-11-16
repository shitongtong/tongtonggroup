//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.helper;

import org.apache.commons.cli.*;
import cn.stt.sprider.enums.ParamEnum;
import cn.stt.sprider.utils.StringUtil;

public class CliHelper {
    private static Options options = new Options();

    static {
        ParamEnum[] var3;
        int var2 = (var3 = ParamEnum.values()).length;

        for(int var1 = 0; var1 < var2; ++var1) {
            ParamEnum e = var3[var1];
            if(StringUtil.isEmpty(e.getValueName())) {
                Option option = new Option(e.getName(), e.isHasArgs(), e.getDesc());
                options.addOption(option);
            } else {
                OptionBuilder.withArgName(e.getValueName());
                OptionBuilder.hasArg();
                OptionBuilder.withDescription(e.getDesc());
                options.addOption(OptionBuilder.create(e.getName()));
            }
        }

    }

    public CliHelper() {
    }

    public static Options getOptions() {
        return options;
    }

    public static CommandLine parse(String[] args) throws ParseException {
        PosixParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);
        return cmd;
    }
}

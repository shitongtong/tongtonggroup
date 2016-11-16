//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.helper;

import org.apache.commons.cli.HelpFormatter;
import cn.stt.sprider.constants.GlobalConfig;

import java.io.PrintWriter;

public class CmdHelper {
    public CmdHelper() {
    }

    public static void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("yispider", CliHelper.getOptions());
    }

    public static void showVersion() {
        PrintWriter pw = new PrintWriter(System.out);
        StringBuffer sb = new StringBuffer("yispider: ");
        sb.append(GlobalConfig.config.getString("version"));
        sb.append(System.getProperty("line.separator"));
        pw.write(sb.toString());
        pw.flush();
        pw.close();
    }
}

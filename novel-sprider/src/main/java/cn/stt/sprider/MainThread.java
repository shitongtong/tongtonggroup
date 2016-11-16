//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider;

import cn.stt.sprider.enums.ParamEnum;
import cn.stt.sprider.helper.CliHelper;
import cn.stt.sprider.helper.CmdHelper;
import cn.stt.sprider.helper.FileHelper;
import cn.stt.sprider.processor.CmdProcessor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {
    private static final Logger logger = LoggerFactory.getLogger(MainThread.class);
    private String[] args;

    public MainThread(String[] args) {
        this.args = args;
    }

    public void run() {
        CommandLine cmd = null;
        try {
            if(this.args == null || this.args.length == 0) {
                this.args = new String[]{"-ca"};
            }

            cmd = CliHelper.parse(this.args);
            if(cmd.hasOption(ParamEnum.HELP.getName())) {
                CmdHelper.showHelp();
                logger.debug("程序退出...");
                System.exit(0);
            } else if(cmd.hasOption(ParamEnum.VERSION.getName())) {
                CmdHelper.showVersion();
                logger.debug("程序退出...");
                System.exit(0);
            } else if(cmd.hasOption(ParamEnum.MULTI.getName())) {
                List e = FileHelper.readRunArgs("run.ini");
                ExecutorService pool = Executors.newFixedThreadPool(e.size());
                Iterator var5 = e.iterator();

                while(var5.hasNext()) {
                    String[] args = (String[])var5.next();
                    CommandLine mcmd = CliHelper.parse(args);
                    pool.execute(new CmdProcessor(mcmd));
                }
            } else {
                CmdProcessor e1 = new CmdProcessor(cmd);
                e1.start();
            }
        } catch (ParseException var7) {
            logger.error("解析命令行参数出错， 请输入help查看用法。", var7);
            logger.debug("程序异常退出...");
            System.exit(0);

        }

    }

    public String[] getArgs() {
        return this.args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}

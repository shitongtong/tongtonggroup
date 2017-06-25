package cn.stt;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24 0024.
 */
public class StartUp {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        File configurationFile = new File(StartUp.class.getResource("/generatorConfig-simple.xml").toURI());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configurationFile);
        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
//        ProgressCallback progressCallback = arguments.containsKey(VERBOSE) ? new VerboseProgressCallback()
//                : null;
        myBatisGenerator.generate(null);
        System.out.println(warnings);
    }
}

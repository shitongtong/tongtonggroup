//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider;

import cn.stt.sprider.constants.GlobalConfig;
import cn.stt.sprider.loader.InitCfgLoader;
import cn.stt.sprider.loader.SimpleLoaderFactory;

public class StartApp {
    public StartApp() {
    }

    public static void main(String[] args) {
        try {
            InitCfgLoader.load();
            SimpleLoaderFactory.create(GlobalConfig.localSite.getProgram()).load();
        } catch (Exception var2) {
            var2.printStackTrace();
            System.exit(-1);
        }

        (new MainThread(args)).run();
    }
}

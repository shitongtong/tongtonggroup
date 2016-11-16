//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.pool2;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import cn.stt.sprider.service.IHtmlBuilder;

public class HtmlBuilderObjectPool {
    private static GenericKeyedObjectPool<String, IHtmlBuilder> htmlBuilderPool;

    private HtmlBuilderObjectPool() {
    }

    public static GenericKeyedObjectPool<String, IHtmlBuilder> getPool() {
        if(htmlBuilderPool == null) {
            Class var0 = HtmlBuilderObjectPool.class;
            synchronized(HtmlBuilderObjectPool.class) {
                if(htmlBuilderPool == null) {
                    htmlBuilderPool = new GenericKeyedObjectPool(new HtmlBuilderPooledObjectFactory());
                }
            }
        }

        return htmlBuilderPool;
    }
}

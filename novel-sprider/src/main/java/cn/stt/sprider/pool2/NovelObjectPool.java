//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.pool2;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import cn.stt.sprider.service.INovelService;

public class NovelObjectPool {
    private static GenericKeyedObjectPool<String, INovelService> novelPool;

    private NovelObjectPool() {
    }

    public static GenericKeyedObjectPool<String, INovelService> getPool() {
        if(novelPool == null) {
            Class var0 = NovelObjectPool.class;
            synchronized(NovelObjectPool.class) {
                if(novelPool == null) {
                    novelPool = new GenericKeyedObjectPool(new NovelPooledObjectFactory());
                }
            }
        }

        return novelPool;
    }
}

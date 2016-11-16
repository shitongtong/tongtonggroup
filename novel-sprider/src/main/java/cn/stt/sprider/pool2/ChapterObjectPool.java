//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.pool2;

import cn.stt.sprider.service.IChapterService;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

public class ChapterObjectPool {
    private static GenericKeyedObjectPool<String, IChapterService> chapterPool;

    private ChapterObjectPool() {
    }

    public static GenericKeyedObjectPool<String, IChapterService> getPool() {
        if(chapterPool == null) {
            Class var0 = ChapterObjectPool.class;
            synchronized(ChapterObjectPool.class) {
                if(chapterPool == null) {
                    chapterPool = new GenericKeyedObjectPool(new ChapterPooledObjectFactory());
                }
            }
        }

        return chapterPool;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.pool2;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import cn.stt.sprider.factory.impl.ServiceFactory;
import cn.stt.sprider.service.IChapterService;

public class ChapterPooledObjectFactory extends BaseKeyedPooledObjectFactory<String, IChapterService> {
    public ChapterPooledObjectFactory() {
    }

    public IChapterService create(String key) throws Exception {
        return (new ServiceFactory()).createChapterService(key);
    }

    public PooledObject<IChapterService> wrap(IChapterService value) {
        return new DefaultPooledObject(value);
    }
}

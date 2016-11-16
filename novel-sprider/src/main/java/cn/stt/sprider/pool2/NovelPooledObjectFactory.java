//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.pool2;

import cn.stt.sprider.factory.impl.ServiceFactory;
import cn.stt.sprider.service.INovelService;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class NovelPooledObjectFactory extends BaseKeyedPooledObjectFactory<String, INovelService> {
    public NovelPooledObjectFactory() {
    }

    public INovelService create(String key) throws Exception {
        return (new ServiceFactory()).createNovelService(key);
    }

    public PooledObject<INovelService> wrap(INovelService value) {
        return new DefaultPooledObject(value);
    }
}

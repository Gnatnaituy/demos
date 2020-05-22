package com.hasaker.zookeeper_demo.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;

/**
 * @author 余天堂
 * @since 2019/11/30 22:28
 * @description 
 */
@Slf4j
public class DemoStringCallback implements AsyncCallback.StringCallback {

    @Override
    public void processResult(int resultCode, String path, Object ctx, String name) {
        log.info("Create path result: {}, {}, {}, {}", resultCode, path, ctx, name);
    }
}

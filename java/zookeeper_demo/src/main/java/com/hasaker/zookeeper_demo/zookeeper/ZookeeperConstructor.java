package com.hasaker.zookeeper_demo.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author 余天堂
 * @since 2019/11/30 21:55
 * @description
 */
@Slf4j
public class ZookeeperConstructor implements Watcher {

    private static final CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("Receive watched event: {}", watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    log.info("ReGet child: {}", zooKeeper.getChildren(watchedEvent.getPath(), true));
                } catch (Exception e) {
                    log.error("{}", e.toString());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperConstructor());
        log.info("Zookeeper stat: {}", zooKeeper.getState());

        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            log.error("Zookeeper session established!");
        }

        long sessionId = zooKeeper.getSessionId();
        byte[] password = zooKeeper.getSessionPasswd();
        log.info("SessionId: {}", sessionId);
        log.info("Password: {}", password);

        // Reuse session with sessionId and password
//        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperConstructor(),
//                sessionId, password);

        // Create ZNode with synchronize API
//        String demoNode = zooKeeper.create("/demo_node_2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        log.info("Success create ZNode: {}", demoNode);

        // Create ZNode with asynchronous API
//        zooKeeper.create("/demo_node_async-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
//                new DemoStringCallback(), "I am the context");

        // Get children node list by synchronize API
        List<String> children = zooKeeper.getChildren("/demo", true);
        log.info("Children for /demo: {}", children);

        for (int i = 4; i < 10; i++) {
            zooKeeper.create("/demo/child_sequential_" + i, ("data" + i + "").getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        }

        // Get children node list by asynchronous API




        Thread.sleep(Integer.MAX_VALUE);
    }
}

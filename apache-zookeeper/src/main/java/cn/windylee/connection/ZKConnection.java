package cn.windylee.connection;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKConnection {

    private ZooKeeper zoo;
    final CountDownLatch connectionLatch = new CountDownLatch(0);

    public ZKConnection(){}

    public ZooKeeper connect(String host) throws IOException, InterruptedException {
        zoo = new ZooKeeper(host, 2000, watchedEvent -> {
            if(watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected){
                connectionLatch.countDown();
            }
        });

        connectionLatch.await();
        return zoo;
    }

    public void close() throws InterruptedException {
        zoo.close();
    }

}

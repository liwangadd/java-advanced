package cn.windylee.manager;

import org.apache.zookeeper.KeeperException;

public interface ZKManager {

     void create(String path, byte[] data) throws KeeperException, InterruptedException;

     Object getZNodeData(String path, boolean watchFlag);

     void update(String path, byte[] data) throws KeeperException, InterruptedException, KeeperException;

}

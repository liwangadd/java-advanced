package cn.windylee.manager;

import cn.windylee.connection.ZKConnection;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKManagerImpl implements ZKManager{

    private ZooKeeper zkeeper;
    private ZKConnection zkConnection;

    public ZKManagerImpl(){
        initialize();
    }

    private void initialize() {
        try {
            zkConnection = new ZKConnection();
            zkeeper = zkConnection.connect("localhost");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zkeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Override
    public Object getZNodeData(String path, boolean watchFlag) {
        try {
            byte[] b = null;
            b = zkeeper.getData(path, null, null);
            String data = new String(b, "UTF-8");
            System.out.println(data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String path, byte[] data) throws KeeperException, InterruptedException, KeeperException {
        int version = zkeeper.exists(path, true).getVersion();
        zkeeper.setData(path, data, version);
    }

    @Override
    public void delete(String path, int version) throws KeeperException, InterruptedException {
        zkeeper.delete(path, version);
    }
}

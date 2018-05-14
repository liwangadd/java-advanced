package cn.windylee;

import cn.windylee.manager.ZKManager;
import cn.windylee.manager.ZKManagerImpl;
import org.apache.zookeeper.KeeperException;
import org.junit.Before;
import org.junit.Test;

public class ZkManagerTest {

    private ZKManager zkManager;

    @Before
    public void initManager() {
        zkManager = new ZKManagerImpl();
    }

    @Test
    public void testCreate() {
        try {
            zkManager.create("/cn", "windylee".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        String name = (String) zkManager.getZNodeData("/cn", false);
        System.out.println(name);
    }

    @Test
    public void testUpdate() {
        try {
            zkManager.update("/cn", "job".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
//            删除所有版本
            zkManager.delete("/cn", -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

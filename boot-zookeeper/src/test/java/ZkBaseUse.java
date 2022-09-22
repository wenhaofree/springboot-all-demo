import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * 下载： docker pull zookeeper:3.5.8
 * 运行：docker run --privileged=true -d --name fwh-zookeeper --publish 2181:2181  -d zookeeper:3.5.8
 *
 * @program: fwh-parent
 * @description: zookeeper基本使用
 * @author: fwh
 * @date: 2021-07-27 10:20
 **/
public class ZkBaseUse {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //创建连接
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,event -> {
            if (event.getState()== Watcher.Event.KeeperState.SyncConnected&&
                    event.getType()==Watcher.Event.EventType.None) {
                System.out.println("连接成功");
                countDownLatch.countDown();
            }
        });
        System.out.println("连接中……");
        countDownLatch.await();
        //创建节点
        zooKeeper.create("/fwh","fwhTest".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建节点/fwh");
        //查看节点
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/fwh", false, stat);
        System.out.println("获取节点："+new String(data));
        //修改节点
        zooKeeper.setData("/fwh","fwh".getBytes(StandardCharsets.UTF_8),stat.getVersion());
        //API使用
    }
}

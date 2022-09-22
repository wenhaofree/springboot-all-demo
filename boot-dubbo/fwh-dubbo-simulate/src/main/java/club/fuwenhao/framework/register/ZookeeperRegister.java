package club.fuwenhao.framework.register;

import com.alibaba.fastjson.JSONObject;
import club.fuwenhao.framework.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZookeeperRegister {

    static CuratorFramework client;

    static {
        client = CuratorFrameworkFactory
                .newClient("localhost:2181", new RetryNTimes(3, 1000));
        client.start();

    }

    private static Map<String, List<URL>> REGISTER = new HashMap<String,List<URL>>();

    public static void regist(String interfaceName, URL url) {
        try {
            String result = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(String.format("/dubbo/service/%s/%s", interfaceName, JSONObject.toJSONString(url)), null);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<URL> get(String interfaceName) {
        List<URL> urlList = new ArrayList<URL>();

        try {
            List<String> result = client.getChildren().forPath(String.format("/dubbo/service/%s", interfaceName));
            for (String urlstr : result) {
                urlList.add(JSONObject.parseObject(urlstr, URL.class));
            }

            REGISTER.put(interfaceName, urlList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlList;
    }
}

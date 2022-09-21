import club.fuwenhao.ZKApplication;
import club.fuwenhao.service.ZkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZKApplication.class)
public class ZkTest {

    @Autowired
    public ZkService zkService;

    @Test
    public void addNode() {
        zkService.addNode("/fwh");
    }

    @Test
    public void checkPathExist() {
        zkService.checkPathExist("/fwh");
    }

    @Test
    public void dataChange() {
        zkService.dataChange("/fwh");
    }

    @Test
    public void nodeChildren() {
        zkService.nodeChildren("/fwh");
    }

    @Test
    public void watchEvent(){
        zkService.watchEvent("/fwh");
    }

    @Test
    public void deleteNode() {
        zkService.deleteNode("/fwh");
    }
}

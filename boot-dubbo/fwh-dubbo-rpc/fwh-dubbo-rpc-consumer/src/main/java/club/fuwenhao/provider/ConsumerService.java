package club.fuwenhao.provider;

import club.fuwenhao.api.ProviderServiceInterface;
import club.fuwenhao.api.User;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Reference
    private ProviderServiceInterface providerService;

    public String test() {
//        String result = HttpClient.get("http://localhost:8081/provider/service");
//        User user = JSONObject.parseObject(result, User.class);
        User user = providerService.getUser();
        return user.getUsername();
    }
}

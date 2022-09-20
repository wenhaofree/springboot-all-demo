package club.fuwenhao.provider;

import club.fuwenhao.api.ProviderServiceInterface;
import club.fuwenhao.api.User;
import org.apache.dubbo.config.annotation.Service;

@Service
public class ProviderService implements ProviderServiceInterface {

    @Override
    public User getUser() {
        return new User("fwh");
    }
}

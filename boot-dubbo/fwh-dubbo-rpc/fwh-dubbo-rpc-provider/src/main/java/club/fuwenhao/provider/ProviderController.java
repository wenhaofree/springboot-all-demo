package club.fuwenhao.provider;


import club.fuwenhao.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping(value = "service")
    public User test() {
        return providerService.getUser();
    }
}

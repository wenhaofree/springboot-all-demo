package club.fuwenhao.provider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "service")
    public String test() {
        return consumerService.test();
    }
}

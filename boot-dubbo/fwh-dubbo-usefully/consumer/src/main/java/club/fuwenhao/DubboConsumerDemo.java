package club.fuwenhao;

import club.fuwenhao.controller.ConsumerInterceptor;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication
public class DubboConsumerDemo implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ConsumerInterceptor());
    }

    @Reference(version = "default")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(DubboConsumerDemo.class);

        DemoService demoService = context.getBean(DemoService.class);
        System.out.println((demoService.sayHello("fwh")));
    }

}

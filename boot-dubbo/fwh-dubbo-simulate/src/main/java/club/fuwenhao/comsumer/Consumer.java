package club.fuwenhao.comsumer;


import club.fuwenhao.framework.ProxyFactory;
import club.fuwenhao.provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String xxx = helloService.sayHello("xxx");
        System.out.println(xxx);


    }
}

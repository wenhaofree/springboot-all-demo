package club.fuwenhao.controller;

import club.fuwenhao.basic.SpringProducer;
import club.fuwenhao.constant.GlobalConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Title: 测试类
 * @ClassName: club.fuwenhao.controller.MQTestController.java
 * @Description:
 * @author: fuwenhao
 * @date: 2022-09-17 3:02 PM
 */
@RestController
@RequestMapping("/MQTest")
public class MQTestController {

    @Resource
    private SpringProducer producer;

    /**
     * 普通消息
     *
     * @param message
     * @return
     */
    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody String message) {
        producer.sendMessage(GlobalConstant.topicKey, message);
        return "消息发送完成";
    }

    /**
     * 事务消息
     *
     * @param message
     * @return
     * @throws InterruptedException
     */
    //这个发送事务消息的例子中有很多问题，需要注意下。
    @PostMapping("/sendTransactionMessage")
    public String sendTransactionMessage(@RequestBody String message) throws InterruptedException {
        producer.sendMessageInTransaction(GlobalConstant.topicKey, message);
        return "消息发送完成";
    }
}
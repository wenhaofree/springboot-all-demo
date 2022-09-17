package club.fuwenhao.basic;

import club.fuwenhao.constant.GlobalConstant;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Title: 消费端
 * @ClassName: club.fuwenhao.basic.SpringConsumer.java
 * @Description:
 * @author: fuwenhao
 * @date: 2022-09-17 3:01 PM
 */
@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroup", topic = GlobalConstant.topicKey, consumeMode = ConsumeMode.CONCURRENTLY)
public class SpringConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("Received message : " + message);
    }
}

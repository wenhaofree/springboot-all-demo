package club.fuwenhao.test;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2020/11/26 3:52 下午
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue("user-rule-fwh-test-queue"),
        exchange = @Exchange(value = "user-rule-fwh-test", type = ExchangeTypes.FANOUT)))
public class FwhRabbitConsumer {
    @RabbitHandler
    public void process(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            System.out.println(msg);
            final DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    super.handleDelivery(consumerTag, envelope, properties, body);
                    System.out.println("接收消息 :   " + new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        channel.basicNack(envelope.getDeliveryTag(), false, false);
                    }
                    //消息确认
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            channel.basicConsume("user-rule-fwh-test-queue", false, defaultConsumer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            TimeUnit.SECONDS.sleep(5);
//            channel.close();
        }
    }
}

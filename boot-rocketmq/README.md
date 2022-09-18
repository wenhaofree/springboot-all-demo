# rocketmq
## 代码功能描述：
- Springboot启动基本生产者和消费者
- Rocketmq消息api：参见rocket_mq_api包
  - 基本单向发送消息
  - 顺序发送消息
  - 批量发送消息
  - 广播发送消息
  - 过滤发送消息
  - 事务发送消息
- ACL控制-待开发

## 相关图解：
> Broker包含多种topic； topic包含多个MessageQueue（消息分区）
<img src="https://img-blog.csdnimg.cn/img_convert/42f7de5addfba8e0693a57b9908d11e5.png">

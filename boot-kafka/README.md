# Kafka

# 服务端搭建
## ZK服务搭建：
- wget https://dlcdn.apache.org/zookeeper/zookeeper-3.5.10/apache-zookeeper-3.5.10-bin.tar.gz
- tar -zxvf apache-zookeeper-3.5.10-bin.tar.gz
- cd apache-zookeeper-3.5.10-bin
- cp conf/zoo_sample.cfg conf/zoo.cfg
- vim zoo.cfg
  - tickTime=2000
    initLimit=10
    syncLimit=5     
    dataDir=/Users/fwh/data/zookeeper      
    clientPort=2181
- ./zkServer.sh start ../conf/zoo.cfg -server  10.253.8.251:2181
- bin/zkCli.sh
  - ls /

## kafka搭建：
- wget https://archive.apache.org/dist/kafka/2.4.1/kafka_2.11-2.4.1.tgz
- tar -zxvf kafka_2.11-2.4.1.tgz
- cd kafka_2.11-2.4.1
- 修改：config/server.properties:
  - #broker.id属性在kafka集群中必须要是唯一
    broker.id=0
    #kafka部署的机器ip和提供服务的端口号
    listeners=PLAINTEXT://10.253.8.251:9092 
    #kafka的消息存储文件
    log.dir=/usr/local/data/kafka‐logs
    #kafka连接zookeeper的地址
    zookeeper.connect=10.253.8.251:2181
- 启动kafka，运行日志在logs目录的server.log文件里
  bin/kafka‐server‐start.sh ‐daemon config/server.properties#后台启动，不会打印日志到控制台
  或者用
  bin/kafka‐server‐start.sh config/server.properties&
- 停止kafka 
  - bin/kafka‐server‐stop.sh
- 创建主题：
  - bin/kafka-topics.sh --create --topic test --replication-factor 1 --partitions 1 --zookeeper localhost:2181
  - –topic指定topic name
    –partitions指定分区数，这个参数需要根据broker数和数据量决定，正常情况下，每个broker上两个partition最好；
    –replication-factor指定partition的replicas数，建议设置为2；
- 删除主题：
  - bin/kafka‐topics.sh ‐‐delete ‐‐topic test ‐‐zookeeper 192.168.65.60:2181
- 发送消息：
  - bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test
  - sh kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test
  >hello world
  >hello kafka

- 消费消息：
  - bin/kafka‐console‐consumer.sh ‐‐bootstrap‐server 127.0.0.1:9092 ‐‐topic test
  - sh kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic test
  - sh kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --from-beginning --topic test
  
- 查看topic的情况：
  - bin/kafka-topics.sh --zookeeper 127.0.0.1:2181 --describe --topic test
 

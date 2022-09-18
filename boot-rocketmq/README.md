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



# RocketMq服务端搭建和使用：

## 服务端启动：

```shell
#环境变量配置：
export ROCKETMQ_HOME=/Users/fwh/dev/rocketmq-all-4.7.1-bin-release
PATH=$ROCKETMQ_HOME/bin:$JAVA_HOME/bin:$PATH:$HOME/.local/bin:$HOME/bin



#进入目录
/Users/fwh/dev/rocketmq-all-4.7.1-bin-release

#启动server 
# 修改runsesrver.sh
JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"

nohup bin/mqnamesrv & 

#启动broker
# 修改runbroker.sh
JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m"
# 修改broker.conf自动生成topic
autoCreateTopicEnable=true

nohup ./mqbroker &

# 日志目录：
/Users/fwh/logs/rocketmqlogs

# 存储目录：
/Users/fwh/store

#验证服务生产者命令：
bin/tools.sh org.apache.rocketmq.example.quickstart.Producer

```

### 本机Mac系统下启动：

```shell
#--------------------------------------------------------
#Mac系统下执行：
nohup sh bin/mqnamesrv & 
nohup sh bin/mqbroker &

#Nameserver启动：
sh mqnamesrv
#broker启动
sh mqbroker -c ~/dev/rocketmq-all-4.7.1-bin-release/conf/broker.properties 


#配置文件修改：
/Users/fwh/dev/rocketmq-all-4.7.1-bin-release/conf/broker.properties
#内容：
brokerIP1 = 192.168.1.8


#控制台展示：
java -jar /Users/fwh/dev/rocketmq-console-ng-1.0.1.jar
端口8080
```





```shell
JAVA_OPT="${JAVA_OPT}-server-Xms512m-Xmx512m-Xmn256m-
JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"


export ROCKETMQ_HOME=/Users/fwh/dev/rocketmq-all-4.7.1-bin-release
PATH=$ROCKETMQ_HOME/bin:$JAVA_HOME/bin:$PATH:$HOME/.local/bin:$HOME/bin


JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m"


autoCreateTopicEnable=true

bin/tools.sh org.apache.rocketmq.example.quickstart.Producer


```

## 服务关闭：

```shell
# 1.关闭NameServer
sh bin/mqshutdown namesrv
# 2.关闭Broker
sh bin/mqshutdown broker
```







# 问题：

## broker启动没有反应？

- https://blog.csdn.net/u011249920/article/details/108217411
- 查看日志，确认是内存配置问题，删除store文件夹下内容；



## broker链接IP的问题？

- https://www.codeleading.com/article/30572524860/
- 修改指定的外网IP链接即可。

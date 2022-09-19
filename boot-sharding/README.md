# ShardingSphere
## 测试类：
- 参见：ShardingJDBCTest

## 版本信息：
- MySQL：8版本
- sharding 4.1.1
- driver:com.mysql.cj.jdbc.Driver
  - 8版本
- driver:com.mysql.jdbc.Driver
  - 8版本一下

## 配置文件信息：
- application01.properties: 普通配置。奇偶数入库
```
1、首先定义一个数据源m1，并对m1进行实际的JDBC参数配置
2、spring.shardingsphere.sharding.tables.course开头的一系列属性 即定义了一个名为course的逻辑表。
actual-data-nodes属性即定义course逻辑表的实际数据分布情况，他 分布在m1.course_1和m1.course_2两个表。
key-generator属性配置了他的主键列以及主键生成策略。 ShardingJDBC默认提供了UUID和SNOWFLAKE两种分布式主键生成策 略。
table-strategy属性即配置他的分库分表策略。分片键为cid属性。分片 算法为course_$->{cid%2+1}，表示按照cid模2+1的结果，然后加上前 面的course__ 部分作为前缀就是他的实际表结果。注意，这个表达式计 算出来的结果需要能够与实际数据分布中的一种情况对应上，否则就会 报错。
sql.show属性表示要在日志中打印实际SQL
3、coursedb的表结构见示例中sql文件夹中的sql语句。
```

- application02.properties: 分库分表示例配置。内置分片算法示例， inline、 standard、complex、hint。广播表配置示例。

- application03.properties: 绑定表示例配置
- application04.properties: 读写分离示例配置


# 面试题：
- 海量数据冲击MySQL优化方案？


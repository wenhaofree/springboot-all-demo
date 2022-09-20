# ElasticSearch

## 服务端搭建使用：

- 下载地址：
    - https://www.elastic.co/cn/downloads/past-releases#elasticsearch
- es和jdk的版本依赖：
    - https://www.elastic.co/cn/support/matrix#matrix_jvm
- 修改config/jvm.options配置文件，调整jvm堆内存大小
    - ‐Xms4g
    - ‐Xmx4g
- 服务启动：
    - sh elasticsearch
    - 访问：127.0.0.1:9002

````json
{
  "name": "bogon",
  "cluster_name": "elasticsearch",
  "cluster_uuid": "X2DxxGiNQoa1jvEY4ud-_g",
  "version": {
    "number": "7.17.3",
    "build_flavor": "default",
    "build_type": "tar",
    "build_hash": "5ad023604c8d7416c9eb6c0eadb62b14e766caff",
    "build_date": "2022-04-19T08:11:19.070913226Z",
    "build_snapshot": false,
    "lucene_version": "8.11.1",
    "minimum_wire_compatibility_version": "6.8.0",
    "minimum_index_compatibility_version": "6.0.0-beta1"
  },
  "tagline": "You Know, for Search"
}
````

- kibana下载
    - https://www.elastic.co/cn/downloads/past-releases#kibana
    - kibana配置：
      s- server.port:5601
      server.host:"localhost"#服务器ip
      elasticsearch.hosts: ["http://localhost:9200"] #elasticsearch的访问地址
      i18n.locale:"zh‐CN"#Kibana汉化
- Elasticsearch安装分词插件
    - 在线安装：
    - #查看已安装插件
      bin/elasticsearch‐pluginlist
      #安装插件
      bin/elasticsearch‐plugininstallanalysis‐icu
      #删除插件
      bin/elasticsearch‐pluginremoveanalysis‐icu
    - 离线安装：
        - https://github.com/medcl/elasticsearch-analysis-ik
- ES-Head安装
  - elasticsearch-head-compile-after.tar.gz
  - 本地安装放入es当中

### ES使用：

- POST _analyze

```json
{
  "analyzer": "standard",
  "text": "我爱你中国"
}
# standard 单字拆分
{
  "analyzer": "ik_smart",
  "text": "中华人民共和国"
}
#ik_smart: 会做最粗粒度的拆分

{
"analyzer":"ik_max_word",
"text":"我爱你中国"
}
#ik_max_word:会将文本做最细粒度的拆分
```

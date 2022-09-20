package club.fuwenhao.elasticsearch.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class JobDetail {

    @JSONField(serialize = false)
    private long id;
    private String area;
    private String exp;
    private String edu;
    private String salary;
    private String job_type;
    private String cmp;
    private String pv;
    private String title;
    private String jd;
    private int age;


    @Override
    public String toString() {
        // 使用FastJSON将一个对象直接转换为JSON字符串
        return id + ":" + JSONObject.toJSONString(this);
    }
}

package cn.sp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Created by 2YSP on 2018/4/29.
 */
@Data
@Document(indexName = "p",type = "person")//必须是小写单词
public class Person implements Serializable{
    /**属性名不支持驼峰式**/
    @Id
    private Integer pid;

    private String first_name;

    private String last_name;

    private Integer age;


}

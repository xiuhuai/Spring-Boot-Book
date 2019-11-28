package com.example.demo.model;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import java.io.Serializable;

/**
 * @author longzhonghua
 * @data 2/24/2019 11:33 PM
 */
@Data
/**
 * 必须实现可序列化接口，要在网络上传输
 */
public class User implements Serializable {

    @Field("id")
    /**
     * 使用这个注释，里面的名字是根据在solr中配置的来决定
     */
    private String id;

    @Field("name")
    private String name;

}


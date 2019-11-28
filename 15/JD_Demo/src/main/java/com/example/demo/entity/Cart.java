package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Cart
 * Author:   longzhonghua
 * Date:     2019/4/12 19:05
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@Entity
@Data
public class Cart extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//由数据库控制,auto是程序统一控制
    /**
     * @Description: 主键id
     */  private Long id;
    /**
     * @Description: 商品id
     */
    private Long product_id;
/**
* @Description: 产品当前名称,可能会变化
*/
    private String product_name;
    /**
     * @Description: 产品数量
     */
    private Long product_num;
    /**
     * @Description: 用户id
     */
    private Long user_id;
    /**
    * @Description: 商品当前价格
    */
    private Float product_price;
    /**
     * @Description: 创建时间
     */
    private Long createTime;
    /**
     * @Description: 修改时间
     */
    @LastModifiedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long updateTime;
}

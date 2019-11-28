package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Product
 * Author:   longzhonghua
 * Date:     2019/4/12 17:35
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@Entity
@Data
public class Product extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//由数据库控制,auto是程序统一控制
   /**
   * @Description: 主键id
   */  private Long id;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "产品名称不能为空")
    /**
     * @Description: 商品名
     */
    private String name;
    /**
     * @Description: 价格
     */
    private Float price;
    /**
     * @Description: 库存数量
     */
    private Double stockCount;
    /**
     * @Description: 秒杀开始时间
     */
    private Long seckill_start_time;
    /**
     * @Description: 秒杀结束时间
     */
    private Long seckill_end_time;
    /**
     * @Description: 分类
     */
    private String category;
    /**
     * @Description: 品牌
     */
    private String brand;
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

package com.example.demo.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

/**
 * Description: ENTITY基类,让实体类去继承时间字段
 * 1.实体头加注解@EntityListeners(AuditingEntityListener.class)
 * 2.启动类加@EnableJpaAuditing
 * 3.
 * @CreatedDate
 * @Column(name = "createTime")
 * private Date createTime;
 * @LastModifiedDate
 * @Column(name = "updateTime")
 * private Date updateTime;
 * 数据库添加相应控制也可以CURRENT_TIMESTAMP ， CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    /*    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        protected Integer id;*/
/*
创建时间
*/
    @CreatedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long createTime;

    /*    最后修改时间*/
    @LastModifiedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long updateTime;


    /*
     * 创建人
     */
    @Column(name = "create_by")
    @CreatedBy
    private Long createBy;
    /*
     * 修改人
     */
    @Column(name = "lastmodified_by")
    @LastModifiedBy
    private String lastmodifiedBy;

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getLastmodifiedBy() {
        return lastmodifiedBy;
    }

    public void setLastmodifiedBy(String lastmodifiedBy) {
        this.lastmodifiedBy = lastmodifiedBy;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
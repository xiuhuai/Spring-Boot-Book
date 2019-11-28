package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;
/**
 * @author longzhonghua
 * @data 2019/01/27 20:44
 */
@Entity
@Data
@Table(name = "stdu")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "enum('male','female')")
    private String sex;
    /**
     * Description:
     * 建立集合，指定关系是一对一，并且申明它在cart类中的名称
     * 关联的表为card表，其主键是id
     * 指定外键名为card_id
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
     private Card card;
}


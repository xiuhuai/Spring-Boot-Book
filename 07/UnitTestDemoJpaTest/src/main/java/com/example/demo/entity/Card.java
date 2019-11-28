package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author longzhonghua
 * @data 2019/01/27 21:51
 */

@Entity
@Table(name = "cardtestjpa")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer num;
 }

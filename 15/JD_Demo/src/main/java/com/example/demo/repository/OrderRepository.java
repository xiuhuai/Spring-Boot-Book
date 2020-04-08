package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: OrderRepository
 * Author:   longzhonghua
 * Date:     2019/4/15 10:50
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
    public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByid(long id);

    }
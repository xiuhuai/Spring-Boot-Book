package com.example.demo.repository;

import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CartRepository
 * Author:   longzhonghua
 * Date:     2019/4/13 12:32
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByid(long id);
    @Query(value = "select * from cart c where c.user_id=:user_id", nativeQuery = true)
    List<Cart> findCartByIdNative(@Param("user_id") long user_id);

}

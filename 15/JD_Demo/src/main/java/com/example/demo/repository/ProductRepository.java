package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ProductRepository
 * Author:   longzhonghua
 * Date:     2019/4/12 19:51
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByid(long id);

}

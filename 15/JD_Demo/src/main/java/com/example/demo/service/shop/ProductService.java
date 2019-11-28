package com.example.demo.service.shop;

import com.example.demo.entity.Product;

import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ProductService
 * Author:   longzhonghua
 * Date:     2019/4/12 19:55
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface ProductService {
    public List<Product> getProductList();
    public Product findProductById(long id);
}

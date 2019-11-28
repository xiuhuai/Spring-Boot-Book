package com.example.demo.service.shop;

import com.example.demo.entity.Cart;

import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CartService
 * Author:   longzhonghua
 * Date:     2019/4/13 12:33
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface CartService {
    public List<Cart> getCartList();
    public Cart findCartById(long id);


}

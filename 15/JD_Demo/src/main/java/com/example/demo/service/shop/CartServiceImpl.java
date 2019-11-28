package com.example.demo.service.shop;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CartServiceImpl
 * Author:   longzhonghua
 * Date:     2019/4/13 12:34
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
//@CacheConfig(cacheNames = "cart")
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<Cart> getCartList() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findCartById(long id) {
        return cartRepository.findByid(id);
    }


}

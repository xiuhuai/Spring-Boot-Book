package com.example.demo.controller.shop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.shop.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CartController
 * Author:   longzhonghua
 * Date:     2019/4/13 12:30
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
@Autowired
private CartService cartService;

    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("")
    public String save(Cart cart)  throws  Exception{
        cartRepository.save(cart);
        //获取保存后的数据表自增id
        Long id=cart.getId();
        //REDIS储存购物车数据.
        Map<String, Object> hashMap = new HashMap();
        /**
        * @Description: 产品id
        */
        hashMap.put("Product_id", cart.getProduct_id());
        /**
         * @Description: 产品名称
         */
        hashMap.put("Product_name", cart.getProduct_name());
        /**
         * @Description: 用户id
         */
        hashMap.put("User_id", cart.getUser_id());
        /**
         * @Description: 购买数量
         */
        hashMap.put("Product_num", cart.getProduct_num());
        /**
         * @Description: 购物车对应的Mysql主键id
         */
        hashMap.put("Cart_id", id);
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(hashMap));
        System.out.println(itemJSONObj);
        String valueStr = JSONObject.toJSONString(itemJSONObj);
        long timestamp = System.currentTimeMillis()/1000;
        /** 
        * @Description: 键
        */
        String  sname=cart.getUser_id().toString();
        redisTemplate.boundZSetOps(sname).add(valueStr,timestamp);
        System.out.println(redisTemplate.opsForZSet().range(sname,0,-1));
        System.out.println(redisTemplate.opsForZSet().size(sname));
        return "redirect:/cart/?user_id="+cart.getUser_id();
    }


    @GetMapping("")

    /**
     * @Description: 购物车不用分页可以用list
     * @Param: [user_id]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: longzhonghua
     * @Date: 2019/4/13
     */
   // @PreAuthorize("principal.id == #user_id")
    /**
    * @Description: 必须要限制登录,或者会报错Failed to evaluate expression 'principal.id.equals(#user_id)'
    */
   @PreAuthorize("principal.id.equals(#user_id)")
    public ModelAndView cartlist(Long user_id, Principal principal) {
               List<Cart> cartList = cartRepository.findCartByIdNative(user_id);
        ModelAndView mav = new ModelAndView("web/shop/cart/list");
        mav.addObject("cartList", cartList);
        return mav;
    }
}

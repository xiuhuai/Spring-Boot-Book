package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: RedisTypeTest
 * Author:   longzhonghua
 * Date:     4/8/2019 9:48 PM
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class RedisTypeTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void string() {
        redisTemplate.opsForValue().set("num", 123);
        redisTemplate.opsForValue().set("string", "some strings");
        Object s = redisTemplate.opsForValue().get("num");
        Object s2 = redisTemplate.opsForValue().get("string");
        System.out.println(s);
        System.out.println(s2);
    }

    @Test
    public void string2() {
        //设置的是3秒失效，3秒之内查询有结果，3秒之后返回为null
        redisTemplate.opsForValue().set("num", "123XYZ", 3, TimeUnit.SECONDS);

        try {
            Object s = redisTemplate.opsForValue().get("num");
            System.out.println(s);
            Thread.currentThread().sleep(2000);
            Object s2 = redisTemplate.opsForValue().get("num");
            System.out.println(s2);
            Thread.currentThread().sleep(5000);
            Object s3 = redisTemplate.opsForValue().get("num");
            System.out.println(s3);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

    @Test
    public void string3() {
        // 覆写(overwrite) 给定 key 所储存的字符串值，从偏移量 offset 开始
        redisTemplate.opsForValue().set("key", "helloworld", 7);
        System.out.println(redisTemplate.opsForValue().get("key"));

    }

    @Test
    public void string4() {
        //设置键的字符串值并返回其旧值
        redisTemplate.opsForValue().set("getSetTest", "test");
        System.out.println(redisTemplate.opsForValue().getAndSet("getSetTest", "test2"));
        System.out.println(redisTemplate.opsForValue().get("getSetTest"));
    }

    @Test
    public void string5() {
        redisTemplate.opsForValue().append("k", "test");
        System.out.println(redisTemplate.opsForValue().get("k"));

        redisTemplate.opsForValue().append("k", "test2");
        System.out.println(redisTemplate.opsForValue().get("k"));

    }

    @Test
    public void string6() {
        redisTemplate.opsForValue().set("key", "1");
        System.out.println(redisTemplate.opsForValue().size("key"));
    }

    @Test
    public void hash1() {
        Map<String, Object> testMap = new HashMap();
        testMap.put("name", "zhonghua");
        testMap.put("sex", "male");
        redisTemplate.opsForHash().putAll("Hash", testMap);
        System.out.println(redisTemplate.opsForHash().entries("Hash"));
    }

    @Test
    public void hash2() {
        redisTemplate.opsForHash().put("redisHash", "name", "hongwei");
        redisTemplate.opsForHash().put("redisHash", "sex", "male");
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        System.out.println(redisTemplate.opsForHash().values("redisHash"));
    }

    @Test
    public void hash3() {
        redisTemplate.opsForHash().put("redisHash", "name", "hongwei");
        redisTemplate.opsForHash().put("redisHash", "sex", "male");
        System.out.println(redisTemplate.opsForHash().delete("redisHash", "name"));
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));

    }

    @Test
    public void hash4() {
        redisTemplate.opsForHash().put("redisHash", "name", "hongwei");
        redisTemplate.opsForHash().put("redisHash", "sex", "male");
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash", "name"));
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash", "age"));


    }

    @Test
    //从键中的哈希获取给定hashKey的值
    public void hash7() {
        redisTemplate.opsForHash().put("redisHash", "name", "hongwei");
        redisTemplate.opsForHash().put("redisHash", "sex", "male");
        System.out.println(redisTemplate.opsForHash().get("redisHash", "name"));
    }

    @Test
    //获取key所对应的散列表的key
    public void hash8() {
        redisTemplate.opsForHash().put("redisHash", "name", "hongwei");
        redisTemplate.opsForHash().put("redisHash", "sex", "male");
        System.out.println(redisTemplate.opsForHash().keys("redisHash"));
    }

    @Test
    //获取key所对应的散列表的大小个数
    public void hash9() {
        redisTemplate.opsForHash().put("redisHash", "name", "hongwei");
        redisTemplate.opsForHash().put("redisHash", "sex", "male");
        System.out.println(redisTemplate.opsForHash().size("redisHash"));
    }


    @Test
    //批量把一个数组插入到列表中
    public void list1() {
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().leftPushAll("list", strings);
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
    }

    @Test
    //返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误。
    public void list2() {
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().leftPushAll("list", strings);
        System.out.println(redisTemplate.opsForList().size("list"));
    }

    @Test
    //将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
    public void list3() {

        redisTemplate.opsForList().leftPush("list", "1");
        System.out.println(redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().leftPush("list", "2");
        System.out.println(redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().leftPush("list", "3");
        System.out.println(redisTemplate.opsForList().size("list"));
    }

    @Test
    //将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
    public void list4() {

        redisTemplate.opsForList().rightPush("listRight", "1");
        System.out.println(redisTemplate.opsForList().size("listRight"));
        redisTemplate.opsForList().rightPush("listRight", "2");
        System.out.println(redisTemplate.opsForList().size("listRight"));
        redisTemplate.opsForList().rightPush("listRight", "3");
        System.out.println(redisTemplate.opsForList().size("listRight"));
    }

    @Test
    //
    public void list5() {

        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list", strings);
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));

    }

    @Test
    //在列表中index的位置设置value值
    public void list6() {
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list6", strings);
        System.out.println(redisTemplate.opsForList().range("list6", 0, -1));
        redisTemplate.opsForList().set("list6", 1, "值");
        System.out.println(redisTemplate.opsForList().range("list6", 0, -1));
    }

    @Test
    //删除列表中存储的列表中第一次次出现的
    public void list7() {
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list7", strings);
        System.out.println(redisTemplate.opsForList().range("list7", 0, -1));
        redisTemplate.opsForList().remove("list7", 1, "2");//将删除列表中存储的列表中第一次次出现的“2”。
        System.out.println(redisTemplate.opsForList().range("list7", 0, -1));
    }

    @Test
    //根据下表获取列表中的值，下标是从0开始的
    public void list8() {
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list8", strings);
        System.out.println(redisTemplate.opsForList().range("list8", 0, -1));
        System.out.println(redisTemplate.opsForList().index("list8", 2));
    }

    @Test
    //弹出最左边的元素，弹出之后该值在列表中将不复存在
    public void list9() {
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list9", strings);
        System.out.println(redisTemplate.opsForList().range("list9", 0, -1));
        System.out.println(redisTemplate.opsForList().leftPop("list9"));
        System.out.println(redisTemplate.opsForList().range("list9", 0, -1));
    }

    @Test
    //弹出最右边的元素，弹出之后该值在列表中将不复存在
    public void list10() {
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list10", strings);
        System.out.println(redisTemplate.opsForList().range("list10", 0, -1));
        System.out.println(redisTemplate.opsForList().rightPop("list10"));
        System.out.println(redisTemplate.opsForList().range("list10", 0, -1));
    }

    @Test
    //无序集合中添加元素，返回添加个数
    public void Set1() {
        String[] strs = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("Set1", strs));
        //也可以直接在add里面添加多个值
        System.out.println(redisTemplate.opsForSet().add("Set1", "1", "2", "3"));
    }

    @Test
    //移除集合中一个或多个成员
    public void Set2() {
        String[] strs = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("Set2", strs));
        System.out.println(redisTemplate.opsForSet().remove("set2", strs));
    }

    @Test
    //移除并返回集合中的一个随机元素
    public void Set3() {
        String[] strs = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("Set3", strs));
        System.out.println(redisTemplate.opsForSet().pop("Set3"));
        System.out.println(redisTemplate.opsForSet().members("Set3"));

    }

    @Test
    //将 member 元素从进行移动
    public void Set4() {
        String[] strs = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("Set4", strs));
        redisTemplate.opsForSet().move("Set4", "str2", "Set4to2");
        System.out.println(redisTemplate.opsForSet().members("Set4"));
        System.out.println(redisTemplate.opsForSet().members("Set4to2"));
    }

    @Test
    //无序集合的大小长度
    public void Set5() {
        String[] strs = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("Set5", strs));
        System.out.println(redisTemplate.opsForSet().size("Set5"));
    }

    @Test
    //返回集合中的所有成员
    public void Set6() {
        String[] strs = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("Set6", strs));
        System.out.println(redisTemplate.opsForSet().members("Set6"));
    }

    @Test
    //遍历set
    public void Set7() {
        String[] strs = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("Set7", strs));
        Cursor<Object> curosr = redisTemplate.opsForSet().scan("Set7", ScanOptions.NONE);
        while (curosr.hasNext()) {
            System.out.println(curosr.next());
        }

    }

    @Test
    //新增一个有序集合
    public void Zset1() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
    }


    @Test
    //新增一个有序集合，存在的话为false，不存在的话为true
    public void Zset2() {
        System.out.println(redisTemplate.opsForZSet().add("zset2", "zset-1", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset2", "zset-1", 1.0));
    }

    @Test
    //从有序集合中移除一个或者多个元素
    public void Zset3() {
        System.out.println(redisTemplate.opsForZSet().add("zset3", "zset-1", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset3", "zset-2", 1.0));
        System.out.println(redisTemplate.opsForZSet().range("zset3", 0, -1));
        System.out.println(redisTemplate.opsForZSet().remove("zset3", "zset-2"));
        System.out.println(redisTemplate.opsForZSet().range("zset3", 0, -1));

    }

    @Test
    //返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
    public void Zset4() {
        System.out.println(redisTemplate.opsForZSet().add("zset4", "zset-1", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset4", "zset-2", 1.0));
        System.out.println(redisTemplate.opsForZSet().range("zset4", 0, -1));
        System.out.println(redisTemplate.opsForZSet().rank("zset4", "zset-1"));


    }

    @Test
    //通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
    public void Zset5() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 9.1);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset5", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset5", 0, -1));


    }


    @Test
    //通过分数返回有序集合指定区间内的成员个数
    public void Zset6() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset6", tuples));
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset6", 0, 9));
        System.out.println(redisTemplate.opsForZSet().count("zset6", 0, 5));


    }

    @Test
    //获取有序集合的成员数
    public void Zset7() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset7", tuples));
        System.out.println(redisTemplate.opsForZSet().size("zset7"));


    }

    @Test
    //获取指定成员的score值
    public void Zset8() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset8", tuples));
        System.out.println(redisTemplate.opsForZSet().score("zset8", "zset-3"));


    }

    @Test
    //获取指定成员的score值
    public void Zset9() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 5.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 2.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset9", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset9", 0, -1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset9", 1, 2));
        System.out.println(redisTemplate.opsForZSet().range("zset9", 0, -1));


    }

    @Test
    //遍历zset
    public void Zset10() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 5.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 2.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset10", tuples));
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("zset10", ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue() + ":" + item.getScore());
        }
    }

    @Test
    //遍历zset
    public void Zsetdss() {

        //有十个库存
        Integer count = 100;
        //添加到redis list中

        for (Integer i = 0; i < count; i++) {
            redisTemplate.opsForList().leftPush("slist", 1);
        }
        System.out.println(redisTemplate.opsForList().range("slist", 0, -1));
    }

    @Test
    //遍历zset
    public void s2() {

        //判断计数器
        if (redisTemplate.opsForList().size("slist") > 0) {
            long user_id = 1903;
            redisTemplate.opsForList().leftPush("ulist", user_id);
        }
        System.out.println(redisTemplate.opsForList().range("slist", 0, -1));
        System.out.println(redisTemplate.opsForList().range("ulist", 0, -1));
    }
}
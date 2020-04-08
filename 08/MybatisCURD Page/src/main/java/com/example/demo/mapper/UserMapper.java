package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2/19/2019 8:21 PM
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User queryById(@Param("id") int id);

    @Select("SELECT * FROM user limit 1000")
    List<User> queryAll();

    @Insert({"INSERT INTO user(name,age) VALUES(#{name},#{age})"})
    int add(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int delById(int id);

    @Update("UPDATE user SET name=#{name},age=#{age} WHERE id = #{id}")
    int updateById(User user);
//
    @Select("SELECT * FROM user limit 1000")
    Page<User> getUserList();
}

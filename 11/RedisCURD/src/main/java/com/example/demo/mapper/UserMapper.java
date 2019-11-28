package com.example.demo.mapper;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author longzhonghua
 * @data 2/21/2019 5:30 PM
 */
@Repository
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,age) values(#{name},#{age})")
    int addUser(@Param("name")String name,@Param("age")String age);

    @Select("select * from user where id =#{id}")
    User findById(@Param("id") String id);


    @Update("update user set name=#{name},age=#{age}  where id=#{id}")
    //void updataById(@Param("id")String id,@Param("name")String name);
  //  void updataById(@Param("id")String id,@Param("name")String name);
  int updateById(User user);


    @Delete("delete from user where id=#{id}")
    void deleteById(@Param("id")String id);
}
package com.example.demo.service.impl;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author longzhonghua
 * @data 2019/01/28 17:51
 */
@CacheConfig(cacheNames = "user")
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    // 查找用户
    @Override
   // @Cacheable(key = "#id")
    //@Cacheable(key="#id",condition ="#id==41" )
    @Cacheable(key="#id",unless="#id.contains(41)" )
    public User findUserById(long id) {
        User user = userRepository.findUserById(id);
        return user;
    }

    // 新增用户
    @Override
    @CachePut(key = "#user.id")
    public User insertUser(User user) {
        user = this.userRepository.save(user);
        return user;
    }

    // 修改用户
    @Override
    @CachePut(key = "#user.id")
    public User updateUserById(User user) {
        return userRepository.save(user);
    }

    // 删除用户
    @Override
    @CacheEvict(key = "#id")
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}

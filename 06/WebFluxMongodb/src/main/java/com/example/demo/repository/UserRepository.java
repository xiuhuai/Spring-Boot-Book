package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserRepository
 * Author:   longzhonghua
 * Date:     3/27/2019 2:21 PM
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
//@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {

}

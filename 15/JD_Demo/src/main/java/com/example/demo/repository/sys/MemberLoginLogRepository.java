package com.example.demo.repository.sys;

import com.example.demo.entity.sys.MemberLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @createdata 3/6/2019 8:41 PM
 * @description 会员登录日志
 */
public interface MemberLoginLogRepository extends JpaRepository<MemberLoginLog,Long> {
    MemberLoginLog findById(long id);

}

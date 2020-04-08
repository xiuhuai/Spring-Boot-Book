package com.example.demo.repository.sys;

import com.example.demo.entity.sys.MemberOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author longzhonghua
 * @createdata 3/6/2019 8:41 PM
 * @description 会员登录日志
 */
public interface MemberOnlineRepository extends JpaRepository<MemberOnline,Long> {
    MemberOnline findById(long id);
    @Transactional
    public void  deleteBySessionId(String sessionId);
}

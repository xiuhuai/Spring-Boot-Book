package com.example.demo.service.sys.impl;

import com.example.demo.entity.sys.MemberLoginLog;
import com.example.demo.repository.sys.MemberLoginLogRepository;
import com.example.demo.service.sys.MemberLonginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author longzhonghua
 * @createdata 3/6/2019 8:47 PM
 * @description
 */
public class MemberLoginLogServiceImpl implements MemberLonginLogService {
    @Autowired
    private MemberLoginLogRepository memberLoginLogRepository;
    @Override
    public List<MemberLoginLog> getMemberLoginLogList() {
        return memberLoginLogRepository.findAll();
    }
}

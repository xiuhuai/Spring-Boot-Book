package com.example.demo.service.sys.impl;

import com.example.demo.entity.sys.SysUserLoginLog;
import com.example.demo.repository.sys.SysUserLoginLogRepository;
import com.example.demo.service.sys.SysUserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysUserLoginLogServiceImpl implements SysUserLoginLogService {
    @Autowired
    private SysUserLoginLogRepository loginRecordRepository;
    @Override
    public List<SysUserLoginLog> getLoginRecordList() {
        return loginRecordRepository.findAll();
    }
}

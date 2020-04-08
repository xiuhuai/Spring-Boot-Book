package com.example.demo.service.sys;

import com.example.demo.entity.sys.SysUserLoginLog;

import java.util.List;

public interface SysUserLoginLogService {
    public List<SysUserLoginLog> getLoginRecordList();
}

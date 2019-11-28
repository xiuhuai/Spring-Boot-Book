package com.example.demo;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MyHealthIndicator
 * Author:   longzhonghua
 * Date:     2019/5/15 13:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Component
public class MyHealthIndicator   extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("自定义状态","OK");
    }
}

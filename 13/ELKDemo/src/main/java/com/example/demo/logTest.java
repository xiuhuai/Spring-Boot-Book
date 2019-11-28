package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: logTest
 * Author:   longzhonghua
 * Date:     3/23/2019 2:22 PM
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Component

public class logTest {

    private Logger logger = LoggerFactory.getLogger(logTest.class);

    @Scheduled(fixedRate = 1000)
    public void logtest() {
        logger.trace("trace日志");
        logger.debug("debug日志");
        logger.info("info日志");
        logger.warn("warn日志");
        logger.error("error日志");
    }

}

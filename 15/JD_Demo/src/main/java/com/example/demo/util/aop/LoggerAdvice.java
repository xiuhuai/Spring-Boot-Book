package com.example.demo.util.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
@Aspect
@Service
public class LoggerAdvice {

	protected Logger logger =  LoggerFactory.getLogger(this.getClass());
	// private static final Logger logger = Logger.getLogger(SysLogAspect.class);
	//定义Pointcut，Pointcut的名称，此方法不能有返回值，该方法只是一个标示
	//前置通知（Before advice） ：在某连接点（JoinPoint）之前执行的通知，但这个通知不能阻止连接点前的执行。
	@Before("within(com.hua..*) && @annotation(loggerManage)")
	//切入点
	public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
		logger.info("执行 " + loggerManage.description() + " 开始");
		logger.info(joinPoint.getSignature().toString());
		logger.info(parseParames(joinPoint.getArgs()));
	}
	//后通知（After advice） ：当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
	@AfterReturning("within(com.hua..*) && @annotation(loggerManage)")
	public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
		logger.info("执行 " + loggerManage.description() + " 结束");
	}
	//抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
	@AfterThrowing(pointcut = "within(com.hua..*) && @annotation(loggerManage)", throwing = "ex")
	public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
		logger.error("执行 " + loggerManage.description() + " 异常", ex);
	}
	/**
	 * 环绕通知（Around advice） ：包围一个连接点的通知，类似Web中Servlet规范中的Filter的doFilter方法。可以在方法的调用前后完成自定义的行为，也可以选择不执行。
	 */
/*
	@Around("controllerAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("=====SysLogAspect 环绕通知开始=====");
		//handleLog(joinPoint, null);
		Object obj= joinPoint.proceed();
		System.out.println("=====SysLogAspect 环绕通知结束=====");
		return  obj;
	}
*/


	private String parseParames(Object[] parames) {
		if (null == parames || parames.length <= 0 || parames.length >1024) {
			return "";
		}
		StringBuffer param = new StringBuffer("传入参数[{}] ");
		for (Object obj : parames) {
			param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
		}
		return param.toString();
	}
	
}

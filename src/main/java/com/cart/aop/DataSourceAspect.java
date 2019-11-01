package com.cart.aop;

import com.cart.annotation.DataSource;
import com.cart.dbconfig.DataSourceEnum;
import com.cart.dbconfig.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源切面处理
 * 用于对方法加注解，执行时会切换数据源，
 * 分库更通用的方法是，拼出dataSourceName，执行下面语句
 * DynamicDataSource.setDataSource(dataSourceName);
 */

@Slf4j
@Aspect
@Component
public class DataSourceAspect
{
	
	@Pointcut("@annotation(com.cart.annotation.DataSource)")
	public void pointCut()
	{
	
	}
	
	@Around("pointCut()")
	public Object doAround(ProceedingJoinPoint point) throws Throwable
	{
		MethodSignature signature=(MethodSignature)point.getSignature();
		Method method=signature.getMethod();
		DataSource ds=method.getAnnotation(DataSource.class);
		
		String dataSource=ds.value();
		if(StringUtils.isBlank(dataSource))
		{
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			log.debug("set datasource is null, use datasource : {}",dataSource);
		}
		else
		{
			DynamicDataSource.setDataSource(dataSource);
			log.debug("use datasource : {}",dataSource);
		}
		
		try
		{
			return point.proceed();
		}
		finally
		{
			DynamicDataSource.clearDataSource();
			log.debug("clear datasource...");
		}
		
	}
	
}

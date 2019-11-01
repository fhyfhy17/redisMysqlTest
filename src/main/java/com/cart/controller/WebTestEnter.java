package com.cart.controller;

import com.cart.dao.repo.PlayerRepository;
import com.cart.dbconfig.DataSourceEnum;
import com.cart.dbconfig.DynamicDataSource;
import com.cart.entry.PlayerEntry;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@Slf4j
public class WebTestEnter
{
	@Autowired
	private PlayerRepository playerRepository;
	@RequestMapping("/test/test1")
	public void test1()
	{
		log.info("测试1");
	}
	
	@RequestMapping("/test/test2")
	public void test2()
	{
		DynamicDataSource.setDataSource(DataSourceEnum.MASTER2.getName());
		List<PlayerEntry> all=playerRepository.findAll();
		all.forEach(
				x->log.info(x.toString())
		);

	}
	@RequestMapping("/test/test3")
	public void test3()
	{
		DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
		List<PlayerEntry> all=playerRepository.findAll();
		all.forEach(
				x->log.info(x.toString())
		);
		
	}
	
	@RequestMapping("/test/test4")
	public void test4()
	{
		
		PlayerEntry p1 = new PlayerEntry();
		p1.setId(1);
		p1.setName("王二");
		
		
		
		
		PlayerEntry p2 = new PlayerEntry();
		p2.setId(2);
		p2.setName("王二");
		log.info(BeanChangeUtil.contrastObj(p1,p2));
	}
	
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException
	{
		PlayerEntry p1 = new PlayerEntry();
		p1.setId(1);
		p1.setName("王二");
		p1.setUserId(2);
		
		PlayerEntry p2 = new PlayerEntry();
		
		BeanUtils.copyProperties(p2,p1);
	
	}
}

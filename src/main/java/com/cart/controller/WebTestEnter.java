package com.cart.controller;

import com.cart.dao.repo.PlayerRepository;
import com.cart.dbconfig.DataSourceEnum;
import com.cart.dbconfig.DynamicDataSource;
import com.cart.entry.PlayerEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

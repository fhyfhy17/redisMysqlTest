package com.cart.entry;

import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;


@Data
public class ModifyFieldsMap{
	
	protected ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
	
}

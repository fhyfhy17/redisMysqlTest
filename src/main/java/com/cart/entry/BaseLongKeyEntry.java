package com.cart.entry;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseLongKeyEntry extends ModifyFieldsMap
{
	@Id
	@GeneratedValue
	protected long id;
	
}

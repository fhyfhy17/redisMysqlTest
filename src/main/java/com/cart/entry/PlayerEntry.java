package com.cart.entry;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="player")
@Data
@NoArgsConstructor
@FieldNameConstants
public class PlayerEntry extends BaseLongKeyEntry{
	
	private String name;
	private long userId;
	private Test test;
}

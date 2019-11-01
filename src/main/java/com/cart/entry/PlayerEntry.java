package com.cart.entry;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="player")
@Data
public class PlayerEntry extends BaseLongKeyEntry{
	
	public PlayerEntry(){
	
	}
	
	private String name;
	private long userId;
	
}

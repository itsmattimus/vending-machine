package com.techelevator.items;

import java.math.BigDecimal;

public class Chip extends Item {

	
	
	public Chip(String name, BigDecimal price, String type, String slotNumber) {
		super(name, price, type, slotNumber);
	
	}

	@Override
	public String getSound() {
		return "Crunch Crunch, Yum!";
	}
}

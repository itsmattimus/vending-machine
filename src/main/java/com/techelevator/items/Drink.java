package com.techelevator.items;

import java.math.BigDecimal;

public class Drink extends Item {

	
	
	public Drink(String name, BigDecimal price, String type, String slotNumber) {
		super(name, price, type, slotNumber);
		
	}

	@Override
	public String getSound() {
		return "Glug Glug, Yum!";
	}
	
}

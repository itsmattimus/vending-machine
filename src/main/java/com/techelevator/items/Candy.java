package com.techelevator.items;

import java.math.BigDecimal;

public class Candy extends Item {

	
	
	public Candy(String name, BigDecimal price, String type, String slotNumber) {
		super(name, price, type, slotNumber);
		
	}

	@Override
	public String getSound() {
		return "Munch Munch, Yum!";
	}

}

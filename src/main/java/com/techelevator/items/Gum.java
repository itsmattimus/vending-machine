package com.techelevator.items;

import java.math.BigDecimal;

public class Gum extends Item {


	public Gum(String name, BigDecimal price, String type, String slotNumber) {
		super(name, price, type, slotNumber);
		
	}

	@Override
	public String getSound() {
		return "Chew chew, Yum!";
	}
}

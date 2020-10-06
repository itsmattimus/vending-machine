package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.techelevator.items.Drink;
import com.techelevator.items.Candy;
import com.techelevator.items.Chip;
import com.techelevator.items.Gum;
import com.techelevator.items.Item;

public class Inventory {
	public Map<String, Item> itemMap = new TreeMap<String,Item>();
	
	public Inventory(String inventoryFile) {
		this.updateInventory(inventoryFile);
	}

	public String grabMenu() {
		String result = "";
		for (Item item : itemMap.values() ) {
			String temp = String.format("%-5s %-20s %10s %10s \n", item.getSlotNumber(), item.getName(), item.getPrice(), item.getType());
			result += temp;
		}
		return result;
	}
	
	private void updateInventory(String inventoryFile) {
		File inputFile = new File(inventoryFile);
		String slotNumber;
		String name;
		BigDecimal price;
		String type;
		
		try (Scanner fs = new Scanner(inputFile)) {
			while(fs.hasNextLine()) {
				//grabbing one line and extracting data, applying that to variables
				String[] fullDescription = fs.nextLine().split("\\|");
				slotNumber = fullDescription[0];
				name = fullDescription[1];
				price = new BigDecimal(fullDescription[2]);
				type = fullDescription[3];
				//make switch to determine what type to instantiate then add it to the itemMap
				switch (type) {
				case "Drink":
					Drink drink = new Drink(name, price, type, slotNumber);
					itemMap.put(slotNumber, drink);
					break;
				case "Candy":
					Candy candy = new Candy(name, price, type, slotNumber);
					itemMap.put(slotNumber, candy);
					break;
				case "Chip":
					Chip chip = new Chip(name, price, type, slotNumber);
					itemMap.put(slotNumber, chip);
					break;
				case "Gum":
					Gum gum = new Gum(name, price, type, slotNumber);
					itemMap.put(slotNumber, gum);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, your file does not exist!");
			e.printStackTrace();
		}
	}
}

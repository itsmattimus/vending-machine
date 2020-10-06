package com.techelevator.view;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import com.techelevator.items.Item;

public class MoneyHandler {
	private BigDecimal machineBalance = new BigDecimal("0.00");
	private BigDecimal currentBalance = new BigDecimal("0.00");

	public String purchase(Item item) {
		String result = "";
		 if (item.getQuantity() == 0) {
				result = "Sorry, that item is OUT OF STOCK";
			} else if (currentBalance.compareTo(new BigDecimal(0.00)) == 0) {
			//if zero then show this
			result = "Please deposit money and/or select a product before finalizing transaction";
		} else if (currentBalance.compareTo(item.getPrice()) >= 0) {
			//if enough money, add to machine subtract from balance, subtract one from quantity, dispense change
			machineBalance = machineBalance.add(item.getPrice());
			currentBalance = currentBalance.subtract(item.getPrice());
			item.setQuantity(item.getQuantity() - 1);
			result = "You just purchased (a) " + item.getName() + " for $" + item.getPrice() + "\n";
			result += item.getSound() + "\n";
			result += "Your remaining balance is $" + currentBalance;
			VendingMachine.recordTransaction(record("purchase", null, item));
		} else {
			result = "MESSAGE: You have insufficient funds for this purchase..";
		}
		
		return result;
	}
	
	public String deposit(BigDecimal money) {
		String result = "";
		currentBalance = currentBalance.add(money);
		VendingMachine.recordTransaction(record("deposit", money, null));
		return result;
	}
	public String dispenseChange(BigDecimal balance) {
		String result = "Here is your change! \n";
		int changeTotal = (int)(balance.doubleValue()*100);
		int quarters = changeTotal/25;
		changeTotal -= (25*quarters);
		result += quarters + " Quarters \n";
		int dimes = changeTotal / 10;
		changeTotal -= (10*dimes);
		result += dimes + " Dimes \n";
		int nickels = changeTotal /5;
		result += nickels + " Nickels \n";
		VendingMachine.recordTransaction(record("dispense", null, null));
		return result;
	}
	
	
	public String record(String transactionType, BigDecimal money, Item item) {
		String result = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		result += dtf.format(now);
		if(transactionType.equals("deposit")) {
			result += " FEED MONEY:  $" + money + "  $" + currentBalance;
		} else if (transactionType.equals("purchase")) {
			result += " " + item.getName() + " " + item.getSlotNumber() + "  $" + item.getPrice() + "  $" + currentBalance;
		} else if (transactionType.equals("dispense")) {
			result += " GIVE CHANGE:  $" + currentBalance + "  $" + "0.00";
		}
		return result;
		
	}
	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	
	
}

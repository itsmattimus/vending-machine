package com.techelevator.view;



import java.math.BigDecimal;

public class VendingMachine {
	private Inventory inventory;
	public MoneyHandler moneyHandler;
	private static FileWriter pw;
	
	public VendingMachine(String inventoryFile) {
		inventory = new Inventory(inventoryFile);
		moneyHandler = new MoneyHandler();
		pw = new FileWriter();
	}
	
	public String showInventory() {
		return inventory.grabMenu();
	} 
	public String selectItem() {
		return "Please select an item";
	}
	public String purchase(String slotNumber) {
		return moneyHandler.purchase(inventory.itemMap.get(slotNumber));
	}
	public String deposit(BigDecimal money) {
		String output;
		moneyHandler.deposit(money);
		output = "Your new balance is: $" + moneyHandler.getCurrentBalance();
		return output;
	}
	public String dispense(BigDecimal balance) {
		return moneyHandler.dispenseChange(balance);
	}
	public BigDecimal getBalance() {
		return moneyHandler.getCurrentBalance();
	}
	public static void recordTransaction(String transactionInfo) {
		pw.transactionHistory.add(transactionInfo);
	}
	public void createAudit() {
		pw.audit();
	}
	public boolean verifySlot(String slot) {
		if (inventory.itemMap.containsKey(slot)) {
			return true;
		}
		return false;
	}
}

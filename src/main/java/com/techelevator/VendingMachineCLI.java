package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_OPTION_FEED_MONEY , SUB_MENU_OPTION_SELECT_PRODUCT, SUB_MENU_OPTION_FINISH_TRANSACTION };
	

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = new VendingMachine("VendingMachine.txt");
		BigDecimal balance = new BigDecimal("0.00");
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, SUB_MENU_OPTIONS);
			String desiredSlot = "";
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//If option 1 is selected
				System.out.println(vendingMachine.showInventory());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while(true) {
					
					menu.displaySubMenuOptions(SUB_MENU_OPTIONS);
					///If Option 2 is selected
					String subMenuChoice;
					subMenuChoice = menu.getChoiceFromSubMenu();
					
					if(subMenuChoice.equals("1")) {
						//If sub option 1 is selected then call deposit
						System.out.println(vendingMachine.deposit(menu.depositInput()));					
					} else if (subMenuChoice.equals("2")) {
						while(true) {
							try {
								System.out.println("Please select an Item: ");
								desiredSlot = menu.getSlotNumber();
								if (!vendingMachine.verifySlot(desiredSlot)) {
									throw new Exception();
								}
								break;
							} catch (Exception e) {
								System.out.println("That slot does not exist..\n");
							}			
						}
						System.out.println("MESSAGE: You have selected: " + desiredSlot);
					} else if (subMenuChoice.equals("3")) {
						System.out.println(vendingMachine.purchase(desiredSlot));
						balance = vendingMachine.getBalance();
						break;
					} else {
						System.out.println("MESSAGE: " + subMenuChoice + " is not a valid option");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							System.out.println("Sleep interrupted");
							e.printStackTrace();
						}
					}
				}					
//				System.out.println(vendingMachine.purchase(menu.slotNumberInput()));
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thanks for visiting my vending machine! See you next time!");
				System.out.println(vendingMachine.dispense(balance)); 
				vendingMachine.createAudit();
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

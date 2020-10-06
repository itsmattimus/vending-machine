package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options, Object[] submenu) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options, submenu);
		}
		return choice;
	}
	public BigDecimal depositInput() {
		String input;
		BigDecimal amount;
		while(true) {
			try {
				System.out.println("Please enter a dollar amount you'd like to deposit: ");
				input = in.nextLine();
				amount = new BigDecimal(input);
				if (amount.compareTo(BigDecimal.ZERO) <= 0) {
					throw new NumberFormatException();
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("MESSAGE: You've entered an invalid dollar amount..");
			}			
		}
		return amount;
	}
	public String slotNumberInput() {
		System.out.println("Please select item.");
		String slotNumber = in.nextLine();
		return slotNumber;
	}

	private Object getChoiceFromUserInput(Object[] options, Object[] submenu) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		} else if (choice.equals("2")) {
			displaySubMenuOptions(submenu);
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> \n");
		out.flush();
	}
	
	
	
	 //----------------Sub Menu-------------------------
	public void displaySubMenuOptions(Object[] submenu) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.out.println("Message: system was interrupted");
			e.printStackTrace();
		}
		out.println("********************************");
		out.println("*Please select an option below.*");
		out.println("********************************");
		for (int i = 0; i < submenu.length; i++) {
			int subMenuOption = i + 1;
			out.println(subMenuOption + ") " + submenu[i]);
		}
		out.flush();
	}
	public String getChoiceFromSubMenu() {
		String subChoice = in.nextLine();
		return subChoice;
	}
	
	public String getSlotNumber() {
		String slotNumber = in.nextLine();
		return slotNumber;
	}
	
}

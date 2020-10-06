package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {
	private PrintWriter pw;
	private File auditFile = new File("auditFile.txt");
	List<String> transactionHistory = new ArrayList<String>();
	public FileWriter() {
		try {
			auditFile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pw = new PrintWriter(auditFile);
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, that file does not exist");
			e.printStackTrace();
		}
	}
	public void audit() {
		for (String str : transactionHistory) {
			pw.println(str);
		}
		pw.flush();
		pw.close();
	}
	public void recordTransaction(String transactionInfo) {
		transactionHistory.add(transactionInfo);
	}
	
}

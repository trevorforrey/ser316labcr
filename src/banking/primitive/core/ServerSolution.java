
/*
  File:	ServerSolution.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is the ServerSolution class
*/

package banking.primitive.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

import banking.primitive.core.Account.State;

class ServerSolution implements AccountServer {

	public ServerSolution() {
		accountMap = new HashMap<String,Account>();
		File file = new File(FILENAME);
		ObjectInputStream in = null;
		try {
			if (file.exists()) {
				System.out.println("Reading from file " + FILENAME + "...");
				in = new ObjectInputStream(new FileInputStream(file));

				Integer sizeI = (Integer) in.readObject();
				int size = sizeI.intValue();
				for (int i=0; i < size; i++) {
					Account acc = (Account) in.readObject();
					if (acc != null) {
						accountMap.put(acc.getName(), acc);
					}
				}
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		finally {
			if (in != null) {
				try {
					in.close();
				} 
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}
	
	public Account getAccount(String name) {
		return accountMap.get(name);
	}
      
  	public List<Account> getActiveAccounts() {
		List<Account> result = new ArrayList<Account>();

		for (Account acc : accountMap.values()) {
			if (acc.getState() != State.CLOSED) {
				result.add(acc);
			}
		}
		return result;
	}

	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountMap.values());
	}
	
	/**
	  Method: closeAccount
	  Inputs: String
	  Returns: boolean value

	  Description:closing an account
	*/
	public boolean closeAccount(String name) {
		Account acc = accountMap.get(name);
		if (acc == null) {
			return false;
		}
		acc.setState(State.CLOSED);
		return true;
	}

	/**
	  Method: newAccount
	  Inputs: String type, String name, float balance
	  Returns: boolean value
	  
      Description: Checks account balance before creating a new account
	*/
	public boolean newAccount(String type, String name, float balance) throws IllegalArgumentException {
		try {
			if (balance < 0.0f) {
				throw new IllegalArgumentException("New account may not be started with a negative balance");
			}
			if (name.equals("")){
				throw new IllegalArgumentException("New account may not be started without a name");
			}
			  return newAccountFactory(type, name, balance);
			}
			catch (IllegalArgumentException exc) {
				return false;
			}
	}
  
   /**
	  Method: newAccountFactory
	  Inputs: String type, String name, float balance
	  Returns: boolean value

	  Description: Validates Account type
	*/
	private boolean newAccountFactory(String type, String name, float balance)
		throws IllegalArgumentException {
		
		if (accountMap.get(name) != null) return false;
		
		Account acc;
		if ("Checking".equals(type)) {
			acc = new Checking(name, balance);
		}
		else if ("Savings".equals(type)) {
			acc = new Savings(name, balance);
		} 
		else {
			throw new IllegalArgumentException("Bad account type:" + type);
		}
		try {
			accountMap.put(acc.getName(), acc);
		} 
		catch (Exception exc) {
			return false;
		}
		return true;
	}
	
	/**
	  Method: saveAccounts
	  Inputs: none
	  Returns: void

	  Description:saves accounts into a HashMap
	*/
	public void saveAccounts() throws IOException {
		
		ObjectOutputStream out = null; 
		FileOutputStream fos = new FileOutputStream(FILENAME);
		try {
			out = new ObjectOutputStream(fos);
			out.writeObject(Integer.valueOf(accountMap.size()));
			
			ArrayList<Account> listOfAccounts = (ArrayList<Account>) getAllAccounts();
			
			for (int i=0; i < listOfAccounts.size(); i++) {
				out.writeObject(listOfAccounts.get(i));
				System.out.println(listOfAccounts.get(i).getName());
			}
			out.close();
			fos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Could not write file:" + FILENAME);
		} 
		finally {
			if (out != null) {
				try {
					out.close();
				} 
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
    }

	static final String FILENAME = "accounts.ser";
	private static final float EMPTY = 0.0f;
	Map<String,Account> accountMap = null;
}


/*
  File:	AccountServer.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is the AccountServer class file
*/

package banking.primitive.core;

import java.io.IOException;
import java.util.List;

/**
  Class: AccountServer

  Description: This is the AccountServer interface
*/

public interface AccountServer {
	
	/**
	  Method: newAccount
	  Inputs: String type, String name, float balance
	  Returns: boolean true if the account was created and stored, false otherwise

	  Description: Create a new account object in the server. if an account already exists with the given name then a new account is not created and stored.
	*/
	public boolean	newAccount(String type, String name, float balance) throws IllegalArgumentException;

	
	/**
	  Method: closeAccount
	  Inputs: String
	  Returns: boolean true if there was an account with this name and close was successful

	  Description: Close an account 
	*/
	public boolean	closeAccount(String name);

	/**
	 * @param name name of the account 
	 * @return Account object or null if not found. 
	 */
	public Account	getAccount(String name);

	/** 
	 * @return a list of all Accounts inside the server 
	 */
	public List<Account> getAllAccounts();

	/** 
	 * @return a list of Accounts inside the server that are not CLOSED
	 */
	public List<Account> getActiveAccounts();

	
	/**
	  Method: saveAccounts
	  Inputs: none
	  Returns: void

	  Description: Saves the state of the server
	*/
	public void	saveAccounts() throws IOException;
}

/*
  File:	AccountServerFactory.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is the AccountServerFactory class file
*/

package banking.primitive.core;


/**
  Class: AccountServerFactory

  Description: This is the AccountServerFactory class
*/

public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	protected AccountServerFactory() {

	}

	/**
	  Method: getMe
	  Inputs: none
	  Returns: An AccountServerFactory object

	  Description: Creates a new AccountServerFactory object when object singleton is null
	*/
	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	/**
	  Method: lookup
	  Inputs: none
	  Returns: a new ServerSolution object

	  Description: creates a ServerSolution object
	*/
	public AccountServer lookup() {
		return new ServerSolution();
	}
}

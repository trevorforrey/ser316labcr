/*
  File:	AccountServerFactory.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is the AccountServerFactory class
*/

package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}

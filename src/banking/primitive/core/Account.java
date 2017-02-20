/*
  File:	Account.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is an Account class file
*/

package banking.primitive.core;


/**
Class:	Account

Description: This is the Account class 
*/

public abstract class Account implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float balance = 0.0F;
    protected String name;
    private State state;

    protected Account(String n) {
        name = n;
        state = State.OPEN;
    }

    protected Account(String n, float b) {
        this(n); 
        balance = b;
    }

    /**
     * @return name of the Account
     */
    public final String getName() {
        return name;
    }

    /**
     * @return balance in the Account
     */
    public final float getBalance() {
        return balance;
    }
   
    /**
	  Method: deposit
	  Inputs: float
	  Returns: boolean value

	  Description: Adds money to an account. May not be done if the account is CLOSED
	*/
    public abstract boolean deposit(float amount);

    /**
     * 
     * 
     * @param parameter
     *            amount is a withdrawal and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     */
    
    /**
	  Method: withdraw
	  Inputs: float
	  Returns: boolean value

	  Description: Takes money out of an account. If the balance falls below 0 then the
                    account is moved to an OVERDRAWN state
	*/
    public abstract boolean withdraw(float amount);

    /**
     * @return either "Checking" or "Savings"
     */
    public abstract String getType();

    protected final State getState() {
        return state;
    }

    protected final void setState(State s) {
        state = s;
    }

    /**
	  Method: toString
	  Inputs: none
	  Returns: formated string

	  Description: String formatter
	*/
    public String toString() {
        return "Account " + name + " has $" + balance + "and is " + getState()
                + "\n";
    }
}

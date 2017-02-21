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

    protected Account(String n) {
        name = n;
        _state = State.OPEN;
    }

    protected Account(String n, float b) {
        this(n); 
        balance = b;
    }

    /**
     * @return balance in the Account
     */
    public final float getBalance() {
        return balance;
    }

    /**
     * @return name of the Account
     */
    public final String getName() {
        return name;
    }
    
    protected final State getState() {
        return _state;
    }
    
    /**
     * @return either "Checking" or "Savings"
     */
    public abstract String getType();
    
    /**
	  Method: deposit
	  Inputs: float
	  Returns: boolean value

	  Description: Adds money to an account. May not be done if the account is CLOSED
	*/
    public abstract boolean deposit(float amount);
    
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
    
    /**
	  Method: withdraw
	  Inputs: float
	  Returns: boolean value

	  Description: Takes money out of an account. If the balance falls below 0 then the
                    account is moved to an OVERDRAWN state
	*/
    public abstract boolean withdraw(float amount);
    
    protected final void setState(State s) {
        _state = s;
    }
    
    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float balance = 0.0F;
    protected String name;
    private State _state;

}

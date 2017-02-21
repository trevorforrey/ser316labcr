/*
  File:	Checking.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is a Checking class file
*/

package banking.primitive.core;


/**
Class:	Checking

Description: This is a checking class
*/

public class Checking extends Account {

	private static final long serialVersionUID = 11L;
	
	private Checking(String name) {
		super(name);
	}
	
	public Checking(String name, float balance) {
		super(name, balance);
	}
	
	public String getType() { 
		return "Checking"; 
	}

	/**
	Method: createChecking
	Inputs: String
	Returns: a new Checking object

	Description: Creates a new Checking object
	*/
  public static Checking createChecking(String name) {
      return new Checking(name);
  }

  /**
	Method: Checking
	Inputs: String, float
	Returns: none
  
  Description: Constructor
	*/
  public Checking(String name, float balance) {
		super(name, balance);
	}

  /**
  Method: deposit
  Inputs: float
  Returns: boolean value
  
  Description:  A deposit may be made unless the Checking account is closed
  */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > EMPTY) {
			balance = balance + amount;
			if (balance >= EMPTY) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}
	
	/**
	  Method: toString
	  Inputs: none
	  Returns: formated string

	  Description: String formatter
	*/
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}

	/**
	  Method: withdraw
	  Inputs: float
	  Returns: boolean value
    
    Description: After 10 withdrawals a fee of $2 is charged per transaction You may continue to withdraw an overdrawn account until the balance is below -$100
	*/
	public boolean withdraw(float amount) {
		if (amount > EMPTY) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				_numWithdraws++;
				if (_numWithdraws > WITHDRAW_THRESHOLD) {
					balance = balance - WITHDRAW_PENALTY;
        }
				if (balance < EMPTY) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	private int _numWithdraws = 0;
	private static final float EMPTY = 0.0f;
	private static final float WITHDRAW_PENALTY = 2.0f;
	private static final int WITHDRAW_THRESHOLD = 10;
}

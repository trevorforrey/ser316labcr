/*
  File:	Savings.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is a Savings class file
*/

package banking.primitive.core;


/**
  Class:	Savings

  Description: This is a Savings class
*/

public class Savings extends Account {
	private static final long serialVersionUID = 111L;

	public Savings(String name) {
		super(name);
	}

	/**
	  Method: Savings
	  Inputs: String, float
	  Returns: none

      Description: Constructor
	*/
	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}
	
	public String getType() { 
		return "Checking";
	}

	/**
	  Method: deposit
	  Inputs: float
	  Returns: boolean value

	  Description: A deposit comes with a fee of 50 cents per deposit
	*/
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > EMPTY) {
			balance = balance + amount -  DEPOSIT_FEE;
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
		return "Savings: " + getName() + ": " + getBalance();
	}

	/**
	  Method: withdraw
	  Inputs: float
	  Returns: boolean value

	  Description: A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	  An account whose balance dips below 0 is in an OVERDRAWN state
	*/
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > EMPTY) {
			balance = balance - amount;
			_numWithdraws++;
			if (_numWithdraws > WITHDRAW_THRESHOLD) {
				balance = balance - WITHDRAW_PENALTY;
            }
			// KG BVA: should be < 0
			if (balance <= EMPTY) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}
	
	private int _numWithdraws = 0;
	private static final float EMPTY = 0.0f;
	private static final float DEPOSIT_FEE = .50f;
	private static final float WITHDRAW_PENALTY = 1.0f;
	private static final int WITHDRAW_THRESHOLD = 3;
}

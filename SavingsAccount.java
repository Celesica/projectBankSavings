package Bank;

import java.util.*;

public class SavingsAccount extends BankAccount {
	
	private int accountNo = 0;
	private double balance = 0;
	private double interestRate = 0.05;
	
	SavingsAccount() {
		
	}
	
	SavingsAccount(String accountName, String address, String contactNumber, String birthday, int accountNo, double balance) {
		super(accountName, address, birthday, contactNumber);
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
//
//	Start of Setter and Getter
//	
	
	public int getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
//	
//	End of Setter and Getter
//	
	
	public double balanceInquiry() {
		return balance;
	}
	
	public void deposit(long amount) {
		double interest = amount * interestRate;
		balance = (balance + amount) + interest;
	}
	
	public void withdraw(long amount) {
		balance = balance - amount;
	}
	
	public boolean validateAcctNumber() {
		return (balance == 0)? false : true;
	}
	
	public void closeAccount(boolean isClose) {
		if(isClose) {
			this.balance = 0;
		}
	}	
}

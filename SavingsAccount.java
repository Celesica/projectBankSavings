public class SavingsAccount extends BankAccount {

	// attributes
	private int accountNo;
	private double balance;
	private double interestRate = 0.05;

	// constructor
	SavingsAccount(String accountName, String address, String contactNumber, String birthday, int accountNo, double balance) {
		super(accountName, address, birthday, contactNumber);
		this.accountNo = accountNo;
		this.balance = balance;
	}

	//	getters and setters
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


	//	methods: balanceInquiry, deposit, withdraw, validateAcctNumber, closeAccount
	public double balanceInquiry() {
		return balance;
	}

	public void deposit(double amount) {
		double interest = amount * interestRate;
		balance = (balance + amount) + interest;
	}

	public void withdraw(double amount) {
		balance = balance - amount;
	}

	public boolean validateAcctNumber() {
		return balance != 0;
	}

	public void closeAccount(boolean isClose) {
		if(isClose) {
			this.balance = 0;
		}
	}
}
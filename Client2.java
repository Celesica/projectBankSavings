import java.util.Scanner;

public class Client2 {
	static int dataCount = 0;
	static boolean isDone = false;
	static Scanner scan = new Scanner(System.in);

	static SavingsAccount[] bank = new SavingsAccount[100];

	// for checking client index inside array
	static int getAccountID(int accountNo) {
		for(int x = 0; bank.length - 1 >= x; x++) {
			if(bank[x] != null) {
				if(bank[x].getAccountNo() == accountNo) {
					return x;
				}
			}
		}
		return -1;
	}

	// validate unique accountNo
	static boolean isExist(int accountNo) {
		for(int x = 0; bank.length - 1 >= x; x++) {
			if(bank[x] != null) {
				if(bank[x].getAccountNo() == accountNo) {
					return true;
				}
			}
		}
		return false;
	}

	// accountNo generator
	static int generateNumber() {
		return (int)(Math.random()*(1000-9999+1)+9999);
	}

	// create New Account
	static int addNewAccount(String accountName, String address, String birthday, String contactNumber, double balance) {
		if(dataCount == 0) {
			int randomAccountNo = generateNumber();
			bank[dataCount] = new SavingsAccount(accountName, address, contactNumber, birthday, randomAccountNo, balance);
			dataCount++;
			return randomAccountNo;
		} else {
			for(int x = 0; dataCount - 1 >= x; x++) {
				int randomAccountNo = generateNumber();
				if(!isExist(randomAccountNo)) {
					bank[dataCount] = new SavingsAccount(accountName, address, contactNumber, birthday, randomAccountNo, balance);
					dataCount++;
					return randomAccountNo;
				}
			}
		}
		return -1;
	}

	// switch case-3 method
	static void depositAccount(int accountNo, double amount) {
		int index = getAccountID(accountNo);
		bank[index].deposit(amount);
	}

	// switch case-4 method
	static void withdrawAccount(int accountNo, double amount) {
		int index = getAccountID(accountNo);
		bank[index].withdraw(amount);
	}

	// switch case-6 method
	static void closeAccount(boolean isClose, int accountNo) {
		int index = getAccountID(accountNo);
		bank[index].closeAccount(isClose);
	}

	// displayMainMenu method
	static int displayMainMenu() {
		int userInputMenu;
		String[] menu = {"[1] New Account", "[2] Balance Inquiry", "[3] Deposit", "[4] Withdraw", "[5] Client Profile", "[6] Close Account", "[7] Exit"};
		System.out.printf(String.format("\n======  JBANK MAIN MENU  ======\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", menu));
		System.out.print("\nPlease select transaction: ");
		userInputMenu = scan.nextInt();
		return userInputMenu;
	}

	// main method
	public static void main(String[] args) {
		int userInput, accountNo;
		double amount;

		do {
			userInput = displayMainMenu(); // show Main Menu

			// cannot use options 2 to 6 unless there's an existing account
			if (userInput > 1 && userInput < 7 && dataCount == 0) {
				System.out.print("<< Option is not available when database is empty.>>\nPlease try again.\n\n");
				continue;
			}

			switch (userInput) {
				case 1: // New Account
					System.out.println("\n****  ADD NEW ACCOUNT  ****");
					System.out.print("Name: ");
					scan.nextLine(); // prevent the next line bug
					String _name = scan.nextLine();
					System.out.print("Address: ");
					String _address = scan.nextLine();
					System.out.print("Birthday: ");
					String _birthday = scan.nextLine();
					System.out.print("Contact No.: ");
					String _contact = scan.nextLine();
					System.out.println("Your Account No: " + addNewAccount(_name, _address, _birthday, _contact,5000));
					System.out.println("\n--------------------------------------------------");
					break;

				case 2: // Balance Inquiry
					System.out.println("\n****  BALANCE INQUIRY  ****");
					System.out.print("Enter Account Number: ");
					accountNo = scan.nextInt();

					if (bank[getAccountID(accountNo)].validateAcctNumber()) {
						System.out.println("Current Balance: ₱" + bank[getAccountID(accountNo)].balanceInquiry());
					} else {
						System.out.println("Sorry, this account is already closed.");
					}
					System.out.println("\n--------------------------------------------------");
					break;

				case 3: // Deposit
					System.out.println("\n****  DEPOSIT  ****");
					System.out.print("Enter Account Number: ");
					accountNo = scan.nextInt();

					System.out.print("Enter Amount: ");
					amount = scan.nextDouble();

					if (bank[getAccountID(accountNo)].validateAcctNumber() && amount < 100){ // cannot deposit less than 100
						System.out.println("Invalid Transaction! Cannot deposit less than ₱100.");
					} else if (bank[getAccountID(accountNo)].validateAcctNumber()) {
						depositAccount(accountNo, amount);
						System.out.println("Transaction Successful!");
					} else {
						System.out.println("Sorry, this account is already closed.");
					}
					System.out.println("\n--------------------------------------------------");
					break;

				case 4: // Withdraw
					System.out.println("\n****  WITHDRAW  ****");
					System.out.print("Enter Account Number: ");
					accountNo = scan.nextInt();

					System.out.print("Enter Amount: ");
					amount = scan.nextInt();

					if (bank[getAccountID(accountNo)].validateAcctNumber() && amount < 100){ // cannot withdraw less than 100
						System.out.println("Invalid Transaction! Cannot withdraw less than ₱100.");
					} else if (bank[getAccountID(accountNo)].validateAcctNumber() && amount == bank[getAccountID(accountNo)].balanceInquiry()) { // cannot withdraw entire balance
						System.out.println("Invalid Transaction! Cannot withdraw entire remaining balance.");
					} else if (bank[getAccountID(accountNo)].validateAcctNumber() && bank[getAccountID(accountNo)].balanceInquiry() - amount < 5000) { // must retain P5000 remaining balance
						System.out.println("Invalid Transaction! Must have maintaining balance of ₱5000.");
					} else if (bank[getAccountID(accountNo)].validateAcctNumber()) {
						withdrawAccount(accountNo, amount);
						System.out.println("Transaction Successful!");
					} else {
						System.out.println("Sorry, this account is already closed.");
					}
					System.out.println("\n--------------------------------------------------");
					break;

				case 5: // Client Profile
					System.out.print("Enter Account Number: ");
					accountNo = scan.nextInt();

					if (bank[getAccountID(accountNo)].validateAcctNumber()) {
						bank[getAccountID(accountNo)].getClientDetails(); // display Client Details
						System.out.println("\tCurrent Balance: ₱" + bank[getAccountID(accountNo)].balanceInquiry()); // Display Current Balance
					} else {
						System.out.println("Sorry, this account is already closed.");
					}
					System.out.println("\n--------------------------------------------------");
					break;

				case 6: // Close Account
					String isClose;
					System.out.print("Enter Account Number: ");
					accountNo = scan.nextInt();
					System.out.println("Do you want to Close this Account? {Y/N}");
					isClose = scan.next().trim().toLowerCase();
					closeAccount((isClose.equals("y")), accountNo);
					System.out.println("Account Closure Successful!");
					System.out.println("\n--------------------------------------------------");
					break;
				case 7:
					isDone = true;
					break;
			}
		} while (!isDone);
	}
}
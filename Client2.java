package Bank;

import java.util.Scanner;

public class Client2 {
	static int Datacount = 0;
	static boolean isDone = false;
	static Scanner scan = new Scanner(System.in);
	
	static SavingsAccount[] bank = new SavingsAccount[100];
	
	static int getAccountID(int accountNo) {
		for(int x = 0; bank.length - 1 >= x; x++) {
			if(bank[x] != null) {
				if((int) bank[x].getAccountNo() == accountNo) {
					return x;
				}
			}
		}
		return -1;
	}
	
	static boolean isExist(int accountNo) {
		for(int x = 0; bank.length - 1 >= x; x++) {
			if(bank[x] != null) {
				if((int) bank[x].getAccountNo() == accountNo) {
					return true;
				}
			}	
		}
		return false;
	}
	
	static int generateNumber() {
		return (int)(Math.random()*(1000-9999+1)+9999);
	}
	
	static int AddAccount(String accountName, String address, String birthday, String contactNumber, double balance) {
		if(Datacount == 0) {
			int randAccount = (int)(Math.random()*(1000-9999+1)+9999);
			bank[Datacount] = new SavingsAccount(accountName, address, contactNumber, birthday, randAccount, balance);
			Datacount++;
			return randAccount;
		} else {
			for(int x = 0; Datacount - 1 >= x; x++) {
				int randAccount = generateNumber();
				if(!isExist(randAccount)) {
					bank[Datacount] = new SavingsAccount(accountName, address, contactNumber, birthday, randAccount, balance);
					Datacount++;
					return randAccount;
				}
			}	
		}
		return -1;
	}
	
	static void DepositAccount(int accountNo, long amount) {
		int INDEX = getAccountID(accountNo);
		bank[INDEX].deposit(amount);
	}
	
	static void WithdrawAccount(int accountNo, long amount) {
		int INDEX = getAccountID(accountNo);
		bank[INDEX].withdraw(amount);
	}
	
	static void CloseAccount(boolean isClose, int accountNo) {
		int INDEX = getAccountID(accountNo);
		bank[INDEX].closeAccount(isClose);
	}
	
	public static void main(String[] args) {
		int uInputMenu, accountNo, amount;
		do {
			
			String[] menu = {"[1] New Account", "[2] Balance Inquiry", "[3] Deposit", "[4] Withdraw", "[5] Client Profile", "[6] Close Account", "[7] Exit"};
			System.out.println(String.format("JBank Main Menu\n%s\n%s\n%s\n%s\n%s\n%s\n%s", menu));
			uInputMenu = scan.nextInt();
			
			if(uInputMenu !=1 && Datacount == 0) {
				System.out.print("Option is not Available when database is empty.\nPlease try again.\n");
				continue;
			}

			switch(uInputMenu) {
			case 1:
				System.out.println("Add new Account");
				System.out.print("Name:");
				scan.nextLine(); // prevent the next line bug
				String _name = scan.nextLine();
				System.out.print("Address:");
				String _address = scan.nextLine();
				System.out.print("Birthday:");
				String _bday = scan.nextLine();
				System.out.print("Contact No.:");
				String _contact = scan.nextLine();
				
				System.out.println("Your Account No: " + AddAccount(_name, _address, _bday, _contact, 5000));
				break;
			case 2:
				System.out.println("Account Number :");
				accountNo = scan.nextInt();

				if(bank[getAccountID(accountNo)].validateAcctNumber()) {
					System.out.println(bank[getAccountID(accountNo)].balanceInquiry());
				} else {
					System.out.println("sorry this account is close.");
				}
				break;
			case 3:
				System.out.println("Account Number :");
				accountNo = scan.nextInt();
				
				System.out.println("Amout to Deposit");
				amount = scan.nextInt();
						
				if(bank[getAccountID(accountNo)].validateAcctNumber()) {
					DepositAccount(accountNo, amount);
				} else {
					System.out.println("sorry this account is close.");
				}
				break;
			case 4:
				System.out.println("Account Number :");
				accountNo = scan.nextInt();
				
				System.out.println("Amout to Withdraw");
				amount = scan.nextInt();
				if(bank[getAccountID(accountNo)].validateAcctNumber()) {
					WithdrawAccount(accountNo, amount);
				} else {
					System.out.println("sorry this account is close.");
				}
				break;
			case 5:
				System.out.println("Account Number :");
				accountNo = scan.nextInt();
				
				if(bank[getAccountID(accountNo)].validateAcctNumber()) {
					bank[getAccountID(accountNo)].getClientDetails();
					System.out.println(bank[getAccountID(accountNo)].balanceInquiry());
				} else {
					System.out.println("sorry this account is close.");
				}
				break;
			case 6:
				String isClose;
				System.out.println("Account Number :");
				accountNo = scan.nextInt();
				System.out.println("Do you want to close an Account? {Y/N}");
				isClose = scan.next();
				
				CloseAccount(((isClose.equals("y"))? true : false), accountNo);
				break;
			case 7:
				isDone = true;
				break;
			case 8:
				for(int x = 0; bank.length - 1 >= x; x++) {
					if(bank[x] != null) {
						System.out.println(String.format("Account no.: %s\tName: %s\tAddress: %s\tBirthday: %s\tContact no.: %s\tBalance: %s", bank[x].getAccountNo(), bank[x].getAccountName(), bank[x].getAddress(), bank[x].getBirthday(), bank[x].getContactNumber(), bank[x].getBalance()));
					}
				}
				break;
			}
		} while(!isDone);
	}
}

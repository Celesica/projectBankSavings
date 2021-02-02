package Bank;

import java.util.*;

public class Client {
	
	static boolean isDone = false;
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Object[]> data = new ArrayList<Object[]>();
	static SavingsAccount bank = new SavingsAccount();
	
	static int getAccountInformation(int accountNo) {
		for(int x = 0; data.size() - 1 >= x; x++) {
			if((int) data.get(x)[0] == accountNo) {
				bank.setAccountNo((int) data.get(x)[0]);
				bank.setAccountName((String) data.get(x)[1]);
				bank.setAddress((String) data.get(x)[2]);
				bank.setBirthday((String) data.get(x)[3]);
				bank.setContactNumber((String) data.get(x)[4]);
				bank.setBalance((double) data.get(x)[5]);
				return x;
			}
		}
		return -1;
	}
	
	static boolean isExist(int accountNo) {
		for(int x = 0; data.size() - 1 >= x; x++) {
			if((int) data.get(x)[0] == accountNo) {
				return true;
			}
		}
		return false;
	}
	
	static Object getData() {
		Object[] DETAILS = {bank.getAccountNo(), bank.getAccountName(), bank.getAddress(), bank.getBirthday(), bank.getContactNumber(), bank.getBalance()};
		return DETAILS;
	}
	
	static int generateNumber() {
		return (int)(Math.random()*(1000-9999+1)+9999);
	}
	
	static int AddAccount(String accountName, String address, String birthday, String contactNumber, double balance) {
		if(data.size() == 0) {
			int randAccount = generateNumber();
			bank = new SavingsAccount(accountName, address, birthday, contactNumber, generateNumber(), balance);
			data.add((Object[]) getData());
			return randAccount;
		} else {
			for(int x = 0; data.size() - 1 >= x; x++) {
				int randAccount = generateNumber();
				if(!isExist(randAccount)) {
					bank = new SavingsAccount(accountName, address, birthday, contactNumber, randAccount, balance);
					data.add((Object[]) getData());
					return randAccount;
				}
			}	
		}
		return -1;
	}
	
	static void DepositAccount(int accountNo, long amount) {
		int INDEX = getAccountInformation(accountNo);
		bank.deposit(amount);
		data.set(INDEX, (Object[]) getData());
	}
	
	static void WithdrawAccount(int accountNo, long amount) {
		int INDEX = getAccountInformation(accountNo);
		bank.withdraw(amount);
		data.set(INDEX, (Object[]) getData());
	}
	
	static void CloseAccount(boolean isClose, int accountNo) {
		int INDEX = getAccountInformation(accountNo);
		bank.closeAccount(isClose);
		data.set(INDEX, (Object[]) getData());
	}
	
	public static void main(String[] args) {
		int uInputMenu, accountNo, amount;
		
		do {
			
			String[] menu = {"[1] New Account", "[2] Balance Inquiry", "[3] Deposit", "[4] Withdraw", "[5] Client Profile", "[6] Close Account", "[7] Exit"};
			System.out.println(String.format("JBank Main Menu\n%s\n%s\n%s\n%s\n%s\n%s\n%s", menu));
			uInputMenu = scan.nextInt();
			
			if(uInputMenu !=1 && data.size() == 0) {
				System.out.print("Option is not Available when database is empty.\nPlease try again.\n");
				continue;
			}
			
			switch(uInputMenu) {
				case 1:
					if (data.size() >= 100) {
						System.out.println("Cannot Add our Data is full");
						continue;
				    }
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
					if(getAccountInformation(accountNo) != -1) {
						System.out.println(String.format("Account no.: %s\nBalance: %s\n", bank.getAccountNo(), bank.getBalance()));
					} else {
						System.out.println("Account number does not exist.");
					}
					break;
				case 3:
					System.out.println("Account Number :");
					accountNo = scan.nextInt();
					
					System.out.println("Amout to Deposit");
					amount = scan.nextInt();
					
					DepositAccount(accountNo, amount);
					break;
				case 4:
					System.out.println("Account Number :");
					accountNo = scan.nextInt();
					
					getAccountInformation(accountNo);
					
					System.out.println("Amout to Withdraw");
					amount = scan.nextInt();
					
					System.out.println(amount > 100);
					System.out.println((bank.balanceInquiry() - amount) > 5000);

					if(bank.balanceInquiry() > 100 && (bank.balanceInquiry() - amount) > 5000) {
						DepositAccount(accountNo, amount);
					} else {
						System.out.println("Php 5,000 should be maintained");
					}
					break;
				case 5:
					System.out.println("Account Number :");
					accountNo = scan.nextInt();
					if(getAccountInformation(accountNo) != -1) {
						System.out.println(String.format("Account no.: %s\nName: %s\nAddress: %s\nBirthday: %s\nContact no.: %s\nBalance: %s\n", bank.getAccountNo(), bank.getAccountName(), bank.getAddress(), bank.getBirthday(), bank.getContactNumber(), bank.getBalance()));
					} else {
						System.out.println("Account number does not exist.");
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
			}
		} while(!isDone);
	}
}

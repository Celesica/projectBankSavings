package Bank;

import java.util.*;

public class Client {
	
	static boolean isDone = false;
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Object[]> data = new ArrayList<Object[]>();
	static SavingsAccount bank = new SavingsAccount();	
	
	static void getAccountInformation(int accountNo) {
		for(int x = 0; data.size() - 1 > x; x++) {
			if((int) data.get(x)[0] == accountNo) {
				bank.setAccountNo((int) data.get(x)[0]);
				bank.setAccountName((String) data.get(x)[1]);
				bank.setAddress((String) data.get(x)[2]);
				bank.setBirthday((String) data.get(x)[3]);
				bank.setContactNumber((String) data.get(x)[4]);
				bank.setBalance((double) data.get(x)[5]);
			}
		}
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
	
	static void AddAccount(String accountName, String address, String birthday, String contactNumber, double balance) {
		if(data.size() == 0) {
			int randAccount = (int)(Math.random()*(1000-9999+1)+9999);
			bank = new SavingsAccount(accountName, address, birthday, contactNumber, randAccount, balance);
			data.add((Object[]) getData());
		} else {
			for(int x = 0; data.size() - 1 >= x; x++) {
				int randAccount = (int)(Math.random()*(1000-9999+1)+9999);
				if(!isExist(randAccount)) {
					bank = new SavingsAccount(accountName, address, birthday, contactNumber, randAccount, balance);
					data.add((Object[]) getData());
					break;
				}
			}	
		}
	}
	
	static void DepositAccount(int accountNo, long amount) {
		getAccountInformation(accountNo);
		bank.deposit(amount);
		data.set(bank.getAccountNo(), (Object[]) getData());
	}
	
	static void WithdrawAccount(int accountNo, long amount) {
		getAccountInformation(accountNo);
		bank.withdraw(amount);
		data.set(bank.getAccountNo(), (Object[]) getData());
	}
	
	static boolean CloseAccount(boolean isClose, int accountNo) {
		getAccountInformation(accountNo);
		bank.closeAccount(isClose);
		data.set(bank.getAccountNo(), (Object[]) getData());
		return false;
	}
	
	public static void main(String[] args) {
		int uInputMenu;
		int accountNo;
		
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
					AddAccount("carl", "carmelo", "jan 20", "09090909", (long) 5000);
					break;
				case 2:
					break;
				case 3:
					System.out.println("Account Number :");
					accountNo = scan.nextInt();
					DepositAccount(accountNo, 5000);
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					String isClose;
					System.out.println("Account Number :");
					accountNo = scan.nextInt();
					System.out.println("Do you want to close an Account? {Y/N}");
					isClose = scan.next();
					CloseAccount(((isClose.toLowerCase() == "y")? true : false), accountNo);
					break;
				case 7:
					break;	
				case 8:
					for(int x = 0; data.size() - 1 >= x; x++) {
						System.out.println(String.format("%s) %s | %s | %s | %s | %s | %s", (x + 1), data.get(x)[0], data.get(x)[1], data.get(x)[2], data.get(x)[3], data.get(x)[4], data.get(x)[5]));
					}
					break;	
			}
		} while(!isDone);
	}
}

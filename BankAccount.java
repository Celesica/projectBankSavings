package Bank;

import java.util.*;

public class BankAccount {
	
	private String accountName, address, birthday, contactNumber;
	
	BankAccount() {
		
	}
	
	BankAccount(String accountName, String address, String birthday, String contactNumber){
		this.accountName = accountName;
		this.address = address;
		this.birthday = birthday;
		this.contactNumber = contactNumber;
	}
	
//
//	Start of Setter and Getter
//	
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
//	
//	End of Setter and Getter
//		
	
	public Object getClientDetails(int accountNo) {
		Object[] ClientDetails = { accountName, address, birthday, contactNumber };
		return ClientDetails;
	}
}

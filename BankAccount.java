public class BankAccount {

	// attributes
	private String accountName = null, address = null, birthday = null, contactNumber = null;

	// constructor
	BankAccount(String accountName, String address, String birthday, String contactNumber){
		this.accountName = accountName;
		this.address = address;
		this.birthday = birthday;
		this.contactNumber = contactNumber;
	}

	// getters and setters

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


	//	getClientDetails method

	public void getClientDetails() {
		System.out.println("\n****  CLIENT PROFILE  ****");
		System.out.println(String.format("\tName: %s\n\tAddress: %s\n\tBirthday: %s\n\tContact No.:%s", accountName, address, birthday, contactNumber));
	}
}
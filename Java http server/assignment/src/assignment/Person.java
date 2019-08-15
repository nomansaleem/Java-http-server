package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the person class which includes all the getters and setter methods for fields 
 * 		such as the name, gender, dob, address and postcode.
 * 
 */

public class Person {
	private String name, gender, dob, address, postcode;
	
	/**
	 * constructor - this has been used in the student class using the super method, as the Student class
	 * extends the person class.
	 * 
	 * @param name - the name of the person
	 * @param gender - the gender of the person
	 * @param dob - the date of birth of the person
	 * @param address - the address of the person
	 * @param postcode - the postcode for the address
	 */
	public Person(String name, String gender, String dob, String address, String postcode) {
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.postcode = postcode;
	}
	
	/**
	 * Below, are all the getters and setter methods used at a later stage.
	 * 
	 */
	
	public String getName() {
		return this.name;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String getDob() {
		return this.dob;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}

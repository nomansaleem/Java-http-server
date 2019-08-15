package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the student class which extends the person class, therefore meaning that
 * 		all the code has from the person class has been implemented into the student class.
 */

public class Student extends Person {
	private int studentNumber;
	private String courseTitle;
	private String startDate;
	private float bursary;
	private String email;
	
	/**
	 * This is the constructor, which uses the super method to implement the variables from
	 * 		the person constructor.
	 * 
	 * @param studentNumber - the student id of the student
	 * @param courseTitle - the course the student is taking
	 * @param startDate - the date they started the course
	 * @param bursary - the bursary the student is receiving
	 * @param email - the students email address
	 */
	
	public Student(String name, String gender, String dob, String address, String postcode,
			int studentNumber, String courseTitle, String startDate, float bursary, String email) 
	{
		super(name, gender, dob, address, postcode);
		
		this.studentNumber = studentNumber;
		this.courseTitle = courseTitle;
		this.startDate = startDate;
		this.bursary = bursary;
		this.email = email;
	}
	
	/**
	 * Below, are all the getter and setter methods for the variables in the Student class.
	 */
	
	public int getStudentNumber() {
		return this.studentNumber;
	}
	
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public String getCourseTitle() {
		return this.courseTitle;
	}
	
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public float getBursary() {
		return this.bursary;
	}
	
	public void setBursary(float bursary) {
		this.bursary = bursary;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}

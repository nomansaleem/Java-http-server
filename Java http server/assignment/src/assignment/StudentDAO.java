package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the StudentDAO class (Data Access Object class) which connects the Java file to the 
 * 		database using the JBDC feature.
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {
	
	/**
	 * This is the method that includes the code for the connection to the database.
	 * 
	 * @return getDBConnection
	 */

	private static Connection getDBConnection() {
		Connection getDBConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:studentdb.sqlite";
			getDBConnection = DriverManager.getConnection(dbURL);
			return getDBConnection;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return getDBConnection;
	}
	
	/**
	 * This is the getAllStudents method which selects all the students from the table 
	 * 		and adds them to the array list called allStudents created in this method.
	 * 
	 * @return allStudents
	 * @throws SQLException
	 */

	public ArrayList<Student> getAllStudents() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		String sql = "SELECT * FROM students";
		
		ArrayList<Student> allStudents = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			// execute SQL query
			resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				Student student = new Student(resultset.getString("Name"), resultset.getString("Gender"),
						resultset.getString("DOB"), resultset.getString("Address"), resultset.getString("Postcode"),
						resultset.getInt("StudentNumber"), resultset.getString("CourseTitle"),
						resultset.getString("StartDate"), resultset.getFloat("Bursary"), resultset.getString("Email"));
				allStudents.add(student);
				// this adds to the student array.
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return allStudents;
	}
	
	/**
	 * This is the getStudent method that holds a String parameter called studentID. The SQL used in this method
	 * 		is selecting the student being called by the StudentNumber.
	 * 
	 * @param studentID
	 * @return null
	 * @throws SQLException
	 */

	public Student getStudent(String studentID) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		String sql = "SELECT * FROM students WHERE StudentNumber = " + studentID;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			// execute SQL query
			resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				System.out.println(resultset.getString("Name") + " " + resultset.getString("StudentNumber"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * This is the insertStu method which holds a Student object called student as the parameter
	 * 		and the functionality of this method is to insert a student into the database.
	 * 
	 * @param student
	 * @return true
	 * @throws SQLException
	 */

	public Boolean insertStu(Student student) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		String sql = "INSERT INTO students(Name, Gender, DOB, Address, Postcode, StudentNumber, CourseTitle, StartDate, Bursary, Email)\n"
				+ "VALUES ('" + student.getName() + "','" + student.getGender() + "','" + student.getDob() + "','" + 
				student.getAddress() + "','" + student.getPostcode() + "','"  + student.getStudentNumber() + "','" + 
				student.getCourseTitle() + "','" + student.getStartDate() + "','" + student.getBursary() + "','" + student.getEmail() + "')";
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			// execute SQL query
			resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				System.out.println(resultset.getString("Name") + " " + resultset.getString("StudentNumber"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return true;
	}
	
	/**
	 * This is the deleteStu method which holds a String parameter called studentNo as this method
	 * 		deletes a student that will be called by the StudentNumber in the database
	 * 
	 * @param studentNo
	 * @return true
	 * @throws SQLException
	 */

	public Boolean deleteStu(String studentNo) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		String sql = "DELETE FROM students WHERE StudentNumber = " + studentNo;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			// execute SQL query
			resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				System.out.println(resultset.getString("Name") + " " + resultset.getString("StudentNumber"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return true;
	}
	
	/**
	 * This is the updateStu method which holds a Student object called student
	 * 		and the SQL statement uses the getters and setter methods from the Student class to get
	 * 		the fields added into the database. E.g. student.getName().
	 * 
	 * @param student
	 * @return true
	 * @throws SQLException
	 */

	public Boolean updateStu(Student student) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		String sql = "UPDATE students SET Name='" + student.getName()+"', Gender='" + student.getGender()+"', DOB= '"+ student.getDob()
		+"', Address= '" + student.getAddress()+"', Postcode= '" + student.getPostcode()+ "', StudentNumber= '" + student.getStudentNumber()
		+"', CourseTitle= '" + student.getCourseTitle()+"', StartDate= '" + student.getStartDate()
		+"', Bursary= '" + student.getBursary()+ "', Email= '" + student.getEmail()+"' WHERE studentNumber="+ student.getStudentNumber();
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			// execute SQL query
			resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				System.out.println(resultset.getString("Name") + " " + resultset.getString("CourseTitle"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return true;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return true
	 * @throws SQLException
	 */

	public Boolean checkLoginCredentials(String username, String password) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		String sql = "SELECT username, password FROM login WHERE loginID = 1";
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			// execute SQL query
			resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				System.out.println(resultset.getString("username") + " " + resultset.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return true;
	}
	
	/**
	 * 
	 * @param key
	 * @return true
	 * @throws SQLException
	 */

	public boolean checkApiKey(String key) throws SQLException {
		return true;
	}
}

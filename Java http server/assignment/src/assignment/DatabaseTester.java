package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the database tester class which I created to test each of my CRUD methods.
 * 
 */

import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

public class DatabaseTester extends StudentDAO {
	/**
	 * This is the main method which includes all the CRUD database operations. E.g.
	 * 		getAllStudents and all the other StudentDAO methods
	 * 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		
		//objects.
		ArrayList<Student> stud = new ArrayList<>();
		StudentDAO dao = new StudentDAO();
		Gson gson = new Gson();
		
		/**
		 * This is the testing operation for the getAllStudents method which
		 * 		adds all the students into the ArrayList.
		 * 
		 */
		try {
			stud = dao.getAllStudents();
			System.out.println("FOUND:"); 
		} catch (SQLException e){
			System.out.println("SQL exception: " + e.getMessage());
		}
		
		String myJson = gson.toJson(stud);
		System.out.println(myJson);
		
		/**
		 * This try catch method is the testing operation for the getStudent method
		 * 		using the StudentNumber.
		 */
		try {
			System.out.println("FOUND STUDENT:");
			dao.getStudent("2000");
		} catch (SQLException e){
			System.out.println("SQL exception: " + e.getMessage());
		}
		
		/**
		 * The variables and the Student s object below are used for the testing operation of insertStu method. 
		 * 
		 */
		
		String name = "Saul Goodman", gender = "M", dob = "23-03-1995", address = "33 Saul Street", postcode = "M21 4SU", courseTitle = "Computer Forensics",
				startDate = "01-09-2016", email = "saulgoodman@mail.com";
		int studentNumber = 5000;
		float bursary = 150;
		
		Student s = new Student(name, gender, dob, address, postcode, studentNumber, courseTitle, startDate, bursary,
				email);
		
		/**
		 * This is the testing operation for the insertStu method which inserts a 
		 * 		student using the student constructor.
		 */
		
		try {
			dao.insertStu(s);
			System.out.println("STUDENT INSERTED.");
		} catch (SQLException e){
			System.out.println("SQL exception: " + e.getMessage());
		}
		
		/**
		 * This is the testing operation for the deleteStu method which deletes
		 * 		a student from the database when a StudentNumber is specified.
		 * In this try catch method, the StudentNumber specified is "3000".
		 * 
		 */

		try {
			dao.deleteStu("3000");
			System.out.println("STUDENT DELETED.");
		} catch(SQLException e) {
			System.out.println("SQL exception: " + e.getMessage());
		}
		
		/**
		 * This is the testing operation for the updateStu method which updates any field you specify
		 * 		in the SQL statement in the StudentDAO class. E.g. the CourseTitle being updated from
		 * 		Computer Science to Software Engineering on a specific student record.
		 */
		
		try {
			dao.updateStu(s);
			System.out.println("UPDATED FIELD.");
		} catch(SQLException e) {
			System.out.println("SQL exception: " + e.getMessage());
		}
		
//		// checks the login credentials.
//		try {
//			dao.checkLoginCredentials("", "");
//		} catch(SQLException e) {
//			System.out.println("SQL exception: " + e.getMessage());
//		}
		
	}

}

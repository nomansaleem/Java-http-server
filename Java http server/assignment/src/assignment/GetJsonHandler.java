package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the GetJsonHandler class, responsible for the /get-json handler, which 
 * 		prints out all the information for every record in the database.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetJsonHandler implements HttpHandler {

	public void handle(HttpExchange he) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		//objects.
		ArrayList<Student> stud = new ArrayList<>();
		StudentDAO dao = new StudentDAO();
		Gson gson = new Gson();

		// adds all the students into the Array List called stud.
		try {
			stud = dao.getAllStudents();
		} catch (SQLException e) {
			System.out.println("SQL exception: " + e.getMessage());
		}
		
		String myJson = gson.toJson(stud);
		
		he.sendResponseHeaders(200, 0);
		out.write(myJson);
		out.close();
	}

}

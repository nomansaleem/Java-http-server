package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the InsertDatabaseHandler class, responsible for the /post handler, which 
 * 		is used to post a new student record into the database using a json object.
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class InsertDatabaseHandler implements HttpHandler {

	public void handle(HttpExchange he) throws IOException {
		StudentDAO dao = new StudentDAO();
		Gson gson = new Gson();
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		System.out.println("Adding New Student:");
		
		HashMap<String, String> post = new HashMap<String, String>();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		String line = "";
		String request = "";
		while ((line = in.readLine()) != null) {
			request = request + line;
		}
		
		System.out.println("\t" + request);
		// individual key=value pairs are delimited by ampersands. Tokenize.
		String[] pairs = request.split("&");
		for (int i = 0; i < pairs.length; i++) {
			// each key=value pair is separated by an equals, and both halves require URL
			// decoding.
			String pair = pairs[i];
			post.put(URLDecoder.decode(pair.split("=")[0], "UTF-8"), URLDecoder.decode(pair.split("=")[1], "UTF-8"));
		}
		
		/**
		 * Below, is the code that uses a json object to post a student.
		 */
		
		String json = post.get("json");
		System.out.println(json);
		Student s= gson.fromJson(json, Student.class);
		
		//inserting student into the database.
		try {
			dao.insertStu(s);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
				
		he.sendResponseHeaders(200, 0);
		out.write("Successfully added student into database.");
		out.close();
	}
}

package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the DeleteHandler class, responsible for the /delete handler, which 
 * 		is used to delete a student record based on the StudentNumber.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DeleteHandler implements HttpHandler {

	public void handle(HttpExchange he) throws IOException {
		StudentDAO dao = new StudentDAO();

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
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
		
		// deletes student from the database.
		try {
			dao.deleteStu(post.get("StudentNumber"));
			System.out.println("STUDENT DELETED.");
		} catch (SQLException e) {
			System.out.println("SQL exception: " + e.getMessage());
		}
				
		he.sendResponseHeaders(200, 0);
		out.write("Student deleted.");
		out.close();
	}

}

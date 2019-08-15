package assignment;

/**
 * @author Noman Saleem - 16007532
 * 
 * This is the web service tester class which is used to test all RESTClient methods
 * 		in the Eclipse console.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.Gson;

public class WebServiceTester {

	static Gson gson; // use this to send and receive json objects.

	public static void main(String[] args) throws IOException {

		gson = new Gson();

		try {
			System.out.println("Students = " + getStudents());
			 postStudent();
			 updateStudent();
			 deleteStudent();
			// getStudent(2000);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
	/**
	 * This is a StringBufer method called getStudents which includes 
	 * 		a URL object which is connected to the 8005 port using the /get-json handler.
	 * 
	 * @return response
	 */

	private static StringBuffer getStudents() {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL("http://localhost:8005/get-json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String output;
			while ((output = reader.readLine()) != null) {
				response.append(output);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	/**
	 * This is another StringBuffer method called getStudent which holds an int parameter
	 * 		called studentId. This method holds a URL which is the /get-student handler.
	 * 
	 * @param studentId
	 * @return response
	 */

	private static StringBuffer getStudent(int studentId) {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL("http://localhost:8005/get-student");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String output;
			while ((output = reader.readLine()) != null) {
				response.append(output);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	/**
	 * This postStudent method is used to insert a json object which includes all the fields
	 * 		needed to insert a new student into the database.
	 * The URL used for this method is the /post handler.
	 * 
	 * @throws IOException
	 */

	private static void postStudent() throws IOException {
		String urlParameters = "json={\"studentNumber\":4000, \"courseTitle\":\"Software Engineering\",\n"
				+ "\"startDate\":\"01-09-2016\",\"bursary\": 500, \"email\":\"hank@mail.com\",\"name\":\"Hank \n"
				+ "Schrader\",\"gender\":\"M\",\"dob\":\"02-03-1996\",\"address\":\"2 Arroya Lane\",\"postcode\":\"M23 2SA\"}";

		URL url = new URL("http://localhost:8005/post");
		// create and configure the connection object
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(15000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		// write/send/POST data to the connection using output stream and buffered
		// writer
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(urlParameters);

		// clear the writer
		writer.flush();
		writer.close();
		// close output stream
		os.close();
		// get the server response code to determine what to do next (i.e.
		// success/error)
		String response = "";
		String line;
		int responseCode = conn.getResponseCode();
		System.out.println("Response Code = " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
				response += line;
			}
		}
		System.out.println("Response = " + response);
		System.out.println("Insert Done.");
	}
	
	/**
	 * This updateStudent method is used to update specific fields in a student record.
	 * 		This method uses the same json object as the postStudent method above.
	 * The URL for this method is the /update method.
	 * 
	 * @throws IOException
	 */

	private static void updateStudent() throws IOException {
		String urlParameters = "json={\"studentNumber\":4000, \"courseTitle\":\"Forensics\",\n"
				+ "\"startDate\":\"01-09-2016\",\"bursary\": 300, \"email\":\"hank@mail.com\",\"name\":\"Hank \n"
				+ "Schrader\",\"gender\":\"M\",\"dob\":\"02-03-1996\",\"address\":\"2 Arroya Lane\",\"postcode\":\"M23 2SA\"}";

		URL url = new URL("http://localhost:8005/update");
		// create and configure the connection object
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(15000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		// write/send/POST data to the connection using output stream and buffered
		// writer
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(urlParameters);

		// clear the writer
		writer.flush();
		writer.close();
		// close output stream
		os.close();
		// get the server response code to determine what to do next (i.e.
		// success/error)
		String response = "";
		String line;
		int responseCode = conn.getResponseCode();
		System.out.println("Response Code = " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
				response += line;
			}
		}
		System.out.println("Response = " + response);
		System.out.println("Update Done.");
	}
	
	/**
	 * This deleteStudent method deletes a student from the database using the StudentNumber.
	 *		/delete is the URL used for this method.
	 * 
	 * @throws IOException
	 */

	private static void deleteStudent() throws IOException {
		String urlParameters = "StudentNumber=\"3000\";";

		URL url = new URL("http://localhost:8005/delete");
		// create and configure the connection object
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(15000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("DELETE");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		// write/send/POST data to the connection using output stream and buffered
		// writer
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(urlParameters);

		// clear the writer
		writer.flush();
		writer.close();
		// close output stream
		os.close();
		// get the server response code to determine what to do next (i.e.
		// success/error)
		String response = "";
		String line;
		int responseCode = conn.getResponseCode();
		System.out.println("Response Code = " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
				response += line;
			}
		}
		System.out.println("Response = " + response);
		System.out.println("Delete Done.");
	}
}
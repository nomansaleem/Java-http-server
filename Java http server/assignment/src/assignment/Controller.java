package assignment;

/**
 * @author Noman Saleem - 16007532
 * @version 1.0
 * 
 * This is the controller class which includes the code for running the server.
 * 
 * This class also includes the url being connected to the handler classes. For example,
 * 		the /get-json url is connected to the GetJsonHandler.
 * 
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Controller {
	
	/**
	 * This is the main method of the Controller class which includes the url being connected to the handler classes. For example,
	 * 		the /get-json url is connected to the GetJsonHandler.
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 * @exception IOException used in the catch body.
	 * @see IOException
	 */
	public static void main(String[] args) {
		try {
			// using port number 8005 to run the server.
			HttpServer server = HttpServer.create(new InetSocketAddress(8005), 0);
			
			server.createContext("/post", new InsertDatabaseHandler());
			server.createContext("/get-student", new ShowHandler());
			server.createContext("/delete", new DeleteHandler());
			server.createContext("/update", new UpdateDatabaseHandler());
			server.createContext("/get-json", new GetJsonHandler());
			
			// start the server
			server.setExecutor(null);
			server.start();
			System.out.println("Server Running: Port 8005.");

		} catch (IOException io) {
			System.out.println("Connection problem: " + io);
		}
	}
}

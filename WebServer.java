//import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
//import java.net.InetAddress;
/**
 * Simple Web Server.
 * 
 * The server opens a TCP port and listens on that port for HTTP requests.
 * The server accepts a port number as an optional parameter.</br>
 * If no parameter is given then it requests one be randomly assigned when
 * opening the TCP server socket.</br>
 * In all cases, the server prints out the port that it is using.
 * 
 * 
 * @author Stephan Jamieson
 * @version 16/02/2016
 */
public class WebServer extends Thread {
	
	Socket client = null;

	//constructor, for use of the Thread class
	//this is called each time a new socket is created by a client
    private WebServer(Socket newClient) {
    	client = newClient;
    }
    
    //start a new thread.
    public void start() {
    	super.start();
    };
    
    // run method for this thread - reads request and compiles a response
    public void run() {	
		try {
			System.out.println( "Client trying to access the server.");
			System.out.println( "Client Details (<ip>:<port>): "+ client.getInetAddress() + ":" + client.getPort());

			Request request = Request.parse(client.getInputStream());

            GetRequestProcessor requestProcessor = new GetRequestProcessor();
			Response response = requestProcessor.process(request);
			
			Response.send(client.getOutputStream(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

    /**
     * Run the web server. The server accepts a port number as an optional parameter.</br>
     * If no parameter is given then it requests one be randomly assigned when opening the TCP server socket.</br>
     * In all cases, the server prints out the port that it is using.
     */
    public static void main(String argv[]) throws Exception {
		// Get the port number from the command line. Use 0 as the default value.
		int port = argv.length>0 ?(new Integer(argv[0])).intValue():0;
		ServerSocket Server = new ServerSocket (port);         
		System.out.println ("GETServer: Waiting for client requests on port - " + port);
		System.out.println ("USE: <ipAdress+port>/path/to/file/<filename>");						
		while(true) {	                	   	      	
	        (new WebServer(Server.accept())).start();
        }

	}

}

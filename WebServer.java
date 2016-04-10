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
//	BufferedReader clientRequest = null;
	DataOutputStream serverResponse = null;

	// TODO : make it call this
    private WebServer(Socket newClient) {
    	client = newClient;
    }
    
    // TODO delete this line
    public void start() {
    	super.start();
    	System.out.println("We have a new connection bitch!!!");
    };
    
public void run() {
		
	System.out.println( "Running, yeah, biach!");
		try {
			System.out.println( "Client Details: "+ client.getInetAddress() + ":" + client.getPort());

			Request request = Request.parse(client.getInputStream());
              
            System.out.println("The HTTP request string is ....");

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
    	System.out.println("IT IS RUNNING BABY");
    	
		// Get the port number from the command line.
		int port = argv.length>0 ?(new Integer(argv[0])).intValue():0;
		ServerSocket Server = new ServerSocket (port, 10, InetAddress.getByName("127.0.0.1"));         
		System.out.println ("TCPServer Waiting for client on port " + port);
								
		while(true) {	                	   	      	
	        (new WebServer(Server.accept())).start();
        }

	}

}

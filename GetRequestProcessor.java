import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
//
import java.nio.charset.StandardCharsets;
/**
 * A GetRequestProcessor contains the logic necessary for handling HTTP GET requests.
 * 
 * @author Stephan Jamieson
 * @version 16/02/2016
 */

public class GetRequestProcessor extends RequestProcessor {

    /**
     * Create a new GetRequestProcessor</br>
     * Calling <code>getRequestMethod()</code> on this object returns <code>HTTPMethodType.GET</code>.
     */
    public GetRequestProcessor() {
        super(HTTPMethodType.GET);
    }
    
    /**
     * Process a given HTTP GET Request message, returning the result in an HTTP Response message.</br>
     */
    public Response process(final Request request) throws Exception {
    	Response response = new Response(request.getHTTPVersion());
    	
    	if (this.canProcess(request.getMethodType())) {
			if (request.getURI().equals("/")) {
				response.setStatus(HTTPStatus.NO_CONTENT);
			} else {
				String fileName = request.getURI().replaceFirst("/", "");
				fileName = URLDecoder.decode(fileName);
				File requestedFile = new File(fileName);
				if (requestedFile.isFile()){
					System.out.println("WE HAVE A BODY!!!!!!!");
					response.setStatus(HTTPStatus.OK);
					byte[] fileData = new byte[(int) requestedFile.length()];
					FileInputStream fileInputStream = new FileInputStream(requestedFile);
					fileInputStream.read(fileData);
					response.setBody(fileData);
					response.setHTMLcontentType(fileName.endsWith(".htm") || fileName.endsWith(".html"));
				}
				else {
					response.setStatus(HTTPStatus.NOT_FOUND);	
				}
			}
    	} else {
    		response.setStatus(HTTPStatus.METHOD_NOT_ALLOWED);
    	}
    	
        assert(this.canProcess(request.getMethodType()));
        return response;
    }
}
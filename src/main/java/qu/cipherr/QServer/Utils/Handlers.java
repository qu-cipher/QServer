package qu.cipherr.QServer.Utils;

import java.net.Socket;

/**
 * For setting custom handlers 
 */
public abstract class Handlers {
    /**
     * Client acceptor method
     * for filtering clients to be accepted in bunch of ways that user specifies
     * (Like UIDs or etc.)
     * If you don't want to filter any, just add the `return true;` to this method.
     * 
     * @param clientSocket
     * @return true/false
     */
    public boolean acceptsClient(Socket clientSocket) {
        return true;
    }

    /**
     * GET Request Handler
     * for handling GET requests from clients.
     * 
     * @param path
     * @param response
     * @return response body
     */
    public String handleGet(String path, Response response) {
        ResponseGenerator generator = response.getResponseGenerator();
        generator.setStatus("HTTP/1.1 200 OK");
        generator.setContentType("text/html");
        String responseBody = "<html><body><h1>Default GET Request Handler</h1></body></html>";
        generator.setResponseBody(responseBody);
        return responseBody;
    }

    /**
     * POST Request Handler
     * for handling POST requests from clients.
     * 
     * @param path
     * @param response
     * @return response body
     */
    public String handlePost(String path, Response response) {
        ResponseGenerator generator = response.getResponseGenerator();
        generator.setStatus("HTTP/1.1 200 OK");
        generator.setContentType("text/html");
        String responseBody = "<html><body><h1>Default POST Request Handler</h1></body></html>";
        generator.setResponseBody(responseBody);
        return responseBody;
    }

    /**
     * PUT Request Handler
     * for handling PUT requests from clients.
     * 
     * @param path
     * @param response
     * @return response body
     */
    public String handlePut(String path, Response response) {
        ResponseGenerator generator = response.getResponseGenerator();
        generator.setStatus("HTTP/1.1 200 OK");
        generator.setContentType("text/html");
        String responseBody = "<html><body><h1>Default PUT Request Handler</h1></body></html>";
        generator.setResponseBody(responseBody);
        return responseBody;
    }

    /**
     * DELETE Request Handler
     * for handling DELETE requests from clients.
     * 
     * @param path
     * @param response
     * @return response body
     */
    public String handleDelete(String path, Response response) {
        ResponseGenerator generator = response.getResponseGenerator();
        generator.setStatus("HTTP/1.1 200 OK");
        generator.setContentType("text/html");
        String responseBody = "<html><body><h1>Default DELETE Request Handler</h1></body></html>";
        generator.setResponseBody(responseBody);
        return responseBody;
    }
}

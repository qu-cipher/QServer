package qu.cipherr.QServer.Utils;

import qu.cipherr.QServer.Extra.HttpContentTypes;
import qu.cipherr.QServer.Extra.HttpStatus;

import java.net.Socket;

/**
 * For setting custom handlers
 */
public abstract class Handler {
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
     * @return Response
     */
    public Response handleGet(String path) {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>Default GET Request Handler for "+ path +"</h1></body></html>");
        return response;
    }

    /**
     * POST Request Handler
     * for handling POST requests from clients.
     *
     * @param path
     * @return Response
     */
    public Response handlePost(String path) {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>Default POST Request Handler for "+ path +"</h1></body></html>");
        return response;
    }

    /**
     * PUT Request Handler
     * for handling PUT requests from clients.
     *
     * @param path
     * @return Response
     */
    public Response handlePut(String path) {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>Default PUT Request Handler for "+ path +"</h1></body></html>");
        return response;
    }

    /**
     * DELETE Request Handler
     * for handling DELETE requests from clients.
     *
     * @param path
     * @return Response
     */
    public Response handleDelete(String path) {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>Default DELETE Request Handler for "+ path +"</h1></body></html>");
        return response;
    }
}

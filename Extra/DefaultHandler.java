package qu.cipherr.QServer.Extra;

import qu.cipherr.QServer.Interfaces.Handler;
import qu.cipherr.QServer.Objects.Response;

import java.net.Socket;

public class DefaultHandler implements Handler {
    @Override
    public boolean acceptsClient(Socket clientSocket) {
        return true; // Accept all clients
    }

    @Override
    public Response handleGet() {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>Welcome to the Home Page</h1></body></html>");
        return response;
    }

    @Override
    public Response handlePost() {
        // Handle POST request
        Response response = new Response();
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>POST method not allowed on this path</h1></body></html>");
        return response;
    }

    @Override
    public Response handlePut() {
        // Handle PUT request
        Response response = new Response();
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>PUT method not allowed on this path</h1></body></html>");
        return response;
    }

    @Override
    public Response handleDelete() {
        // Handle DELETE request
        Response response = new Response();
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>DELETE method not allowed on this path</h1></body></html>");
        return response;
    }
}

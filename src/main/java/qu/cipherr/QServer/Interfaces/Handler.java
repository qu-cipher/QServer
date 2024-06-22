package qu.cipherr.QServer.Interfaces;

import qu.cipherr.QServer.Objects.Response;

import java.net.Socket;

/**
 * For setting custom handlers
 */
public interface Handler {
    /**
     * Client acceptor method
     * for filtering clients to be accepted in a bunch of ways that user specifies
     * (Like UIDs or etc.)
     * If you don't want to filter any, just add the `return true;` to this method.
     *
     * @param clientSocket
     * @return true/false
     */
    boolean acceptsClient(Socket clientSocket);

    /**
     * GET Request Handler
     * for handling GET requests from clients.
     *
     * @return Response
     */
    Response handleGet();

    /**
     * POST Request Handler
     * for handling POST requests from clients.
     *
     * @return Response
     */
    Response handlePost();

    /**
     * PUT Request Handler
     * for handling PUT requests from clients.
     *
     * @return Response
     */
    Response handlePut();

    /**
     * DELETE Request Handler
     * for handling DELETE requests from clients.
     *
     * @return Response
     */
    Response handleDelete();
}

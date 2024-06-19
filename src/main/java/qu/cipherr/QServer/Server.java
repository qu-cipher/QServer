package qu.cipherr.QServer;

import java.io.*;
import java.net.*;

import qu.cipherr.QServer.Utils.*;

/**
 * Represents a basic HTTP server that handles incoming client requests.
 */
public class Server {
    // private HashMap<String, String> serverCredentials = new HashMap<>();
    private int port = 12345;
    private boolean debug = false;
    private Handlers handler;

    /**
     * Constructs a server instance with a specified port and debug mode.
     *
     * @param port      The port number on which the server will listen.
     * @param debugMode True to enable debug mode, false otherwise.
     */
    public Server(int port, boolean debugMode) {
        if (port > 65535) {
            Logger.warn("Port number is greater than `65535`! Using the default port number (12345)");

            this.port = 12345;
            this.debug = debugMode;

            Logger.in("Port set to `12345` and debug mode is `" + debugMode + "`");
        } else {
            this.port = port;
            this.debug = debugMode;

            Logger.in("Port set to " + port + " and debug mode is " + debugMode);
        }

        handler = new Handlers() {};
    }

    /**
     * Sets a custom request handler for the server.
     *
     * @param handler The custom request handler implementing the Handlers interface.
     */
    public void setHandler(Handlers handler) {
        this.handler = handler;
    }

    /**
     * Starts the server, listening for incoming client requests and handling them accordingly.
     * This method blocks until an exception occurs or the server is shut down.
     */
    public void start() {
        if (debug) Logger.debug("Debug mode is ON"); // Check debug mode

        try (ServerSocket serverSocket = new ServerSocket(port)) { // Create server socket
            Logger.in("Server listening on port " + port + "..."); // Log server start

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept client connections
                if (handler.acceptsClient(clientSocket)) { // Check if client is accepted
                    InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                    BufferedReader in = new BufferedReader(isr);

                    String requestLine = in.readLine(); // Read the request line
                    if (requestLine != null && !requestLine.isEmpty()) {
                        String[] parts = requestLine.split(" ");
                        if (parts.length > 1) {
                            String method = parts[0]; // Extract HTTP method
                            String path = parts[1]; // Extract request path
                            Response response = new Response(); // Create a response object

                            // Call appropriate handler method based on HTTP method
                            switch (method) {
                                case "GET":
                                    if (debug) Logger.debug("Handling GET request for path: " + path);
                                    handler.handleGet(path, response);
                                    break;
                                case "POST":
                                    if (debug) Logger.debug("Handling POST request for path: " + path);
                                    handler.handlePost(path, response);
                                    break;
                                case "PUT":
                                    if (debug) Logger.debug("Handling PUT request for path: " + path);
                                    handler.handlePut(path, response);
                                    break;
                                case "DELETE":
                                    if (debug) Logger.debug("Handling DELETE request for path: " + path);
                                    handler.handleDelete(path, response);
                                    break;
                                default:
                                    Logger.warn("Unsupported HTTP method: " + method);
                                    response.setStatus("HTTP/1.1 405 Method Not Allowed");
                                    response.setResponseBody("<html><body><h1>Method Not Allowed</h1></body></html>");
                                    break;
                            }

                            String responseData = response.generate(); // Generate the response
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Send the response
                            out.println(responseData);
                        }
                    }
                }
            }
        } catch (IOException e) { // Error handling
            Logger.err(e.getMessage() + " : " + e.getCause());
        }
    }
}

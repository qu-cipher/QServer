package qu.cipherr.QServer;

import java.io.*;
import java.net.*;
import qu.cipherr.QServer.Extra.*;
import qu.cipherr.QServer.Utils.*;

/**
 * Represents a basic HTTP server that handles incoming client requests.
 */
public class Server {
    private int port = 12345;
    private boolean debug = false;
    private Handler handler;

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
        } else {
            this.port = port;
        }
        this.debug = debugMode;
        Logger.in("Port set to " + this.port + " and debug mode is " + this.debug);
        this.handler = new Handler() {}; // Default handler which does nothing
    }

    /**
     * Sets a custom request handler for the server.
     *
     * @param handler The custom request handler implementing the Handlers interface.
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * Starts the server, listening for incoming client requests and handling them accordingly.
     * This method blocks until an exception occurs or the server is shut down.
     */
    public void start() {
        if (debug) Logger.debug("Debug mode is ON");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Logger.in("Server listening on port " + port + "...");

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    if (handler.acceptsClient(clientSocket)) {
                        handleClient(clientSocket);
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    Logger.err("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            Logger.err("Server error: " + e.getMessage() + " : " + e.getCause());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }

            String method = Request.getType(requestLine);
            String path = Request.getPath(requestLine);
            if (debug) Logger.debug("Received request: " + method + " " + path);

            Response result;
            switch (method) {
                case "GET":
                    if (debug) Logger.debug("Handling GET request for path: " + path);
                    result = handler.handleGet(path);
                    break;
                case "POST":
                    if (debug) Logger.debug("Handling POST request for path: " + path);
                    result = handler.handlePost(path);
                    break;
                case "PUT":
                    if (debug) Logger.debug("Handling PUT request for path: " + path);
                    result = handler.handlePut(path);
                    break;
                case "DELETE":
                    if (debug) Logger.debug("Handling DELETE request for path: " + path);
                    result = handler.handleDelete(path);
                    break;
                default:
                    Logger.warn("Unsupported HTTP method: " + method);
                    result = new Response();
                    result.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
                    result.setContentType(HttpContentTypes.TEXT_HTML);
                    result.setResponseBody("<html><body><h1>Method not allowed</h1></body></html>");
                    break;
            }

            out.println(result.generate());

        } catch (IOException e) {
            Logger.err("Error reading request: " + e.getMessage());
        }
    }
}

package qu.cipherr.QServer;

import java.io.*;
import java.net.*;
import java.util.*;
import qu.cipherr.QServer.Extra.*;
import qu.cipherr.QServer.Objects.Request;
import qu.cipherr.QServer.Objects.Response;
import qu.cipherr.QServer.Objects.Route;
import qu.cipherr.QServer.Utils.*;
import qu.cipherr.QServer.Interfaces.*;

/**
 * Represents a basic HTTP router that handles incoming client requests.
 */
public class Router {
    private int port = 12345;
    private boolean debug = false;
    private Map<Route, String> routes = new HashMap<>();

    /**
     * Constructs a router instance with a specified port, debug mode, and routes.
     *
     * @param port      The port number on which the router will listen.
     * @param debugMode True to enable debug mode, false otherwise.
     * @param routes    The routes to be added to the router.
     */
    public Router(int port, boolean debugMode, Route... routes) {
        if (port > 65535) {
            Logger.warn("Port number is greater than `65535`! Using the default port number (12345)");
            this.port = 12345;
        } else {
            this.port = port;
        }
        this.debug = debugMode;
        Logger.in("Port set to " + this.port + " and debug mode is " + this.debug);

        // Add routes to the router
        for (Route route : routes) {
            addRoute(route, route.getPath());
        }
    }

    public void addRoute(Route route, String path) {
        routes.put(route, path);
    }

    /**
     * Starts the router, listening for incoming client requests and handling them accordingly.
     * This method blocks until an exception occurs or the router is shut down.
     */
    public void start() {
        if (debug) Logger.debug("Debug mode is ON");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Logger.in("Router listening on port " + port + "...");

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                    clientSocket.close();
                } catch (IOException e) {
                    Logger.err("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            Logger.err("Router error: " + e.getMessage() + " : " + e.getCause());
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

            Route route = findRouteByPath(path);
            Response result;
            if (route != null) {
                Handler handler = route.getHandler();
                result = handleRequest(handler, method);
            } else {
                Logger.warn("No route found for path: " + path);
                result = new Response();
                result.setStatus(HttpStatus.NOT_FOUND);
                result.setContentType(HttpContentTypes.TEXT_HTML);
                result.setResponseBody("<html><body><h1>404 Not Found</h1></body></html>");
            }

            out.println(result.generate());

        } catch (IOException e) {
            Logger.err("Error reading request: " + e.getMessage());
        }
    }

    private Route findRouteByPath(String path) {
        for (Map.Entry<Route, String> entry : routes.entrySet()) {
            if (entry.getValue().equals(path)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Response handleRequest(Handler handler, String method) {
        switch (method) {
            case "GET":
                if (debug) Logger.debug("Handling GET request");
                return handler.handleGet();
            case "POST":
                if (debug) Logger.debug("Handling POST request");
                return handler.handlePost();
            case "PUT":
                if (debug) Logger.debug("Handling PUT request");
                return handler.handlePut();
            case "DELETE":
                if (debug) Logger.debug("Handling DELETE request");
                return handler.handleDelete();
            default:
                Logger.warn("Unsupported HTTP method: " + method);
                Response response = new Response();
                response.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
                response.setContentType(HttpContentTypes.TEXT_HTML);
                response.setResponseBody("<html><body><h1>Method not allowed</h1></body></html>");
                return response;
        }
    }
}

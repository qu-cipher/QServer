package qu.cipherr.QServer;

import java.io.*;
import java.net.*;
import java.util.*;
import qu.cipherr.QServer.Extra.*;
import qu.cipherr.QServer.Objects.*;
import qu.cipherr.QServer.Utils.*;
import qu.cipherr.QServer.Interfaces.*;

public class Router {
    private int port = 12345;
    private boolean debug = false;
    private Map<Route, String> routes = new HashMap<>();
    private Map<MediaRoute, String> mediaRoutes = new HashMap<>();

    public Router(int port, boolean debugMode) {
        if (port > 65535) {
            Logger.warn("Port number is greater than `65535`! Using the default port number (12345)");
            this.port = 12345;
        } else {
            this.port = port;
        }
        this.debug = debugMode;
        Logger.in("Port set to " + this.port + " and debug mode is " + this.debug);
    }

    public void addRoute(Route route) {
        routes.put(route, route.getPath());
    }

    public void addMediaRoute(MediaRoute route) {
        mediaRoutes.put(route, route.getPath());
    }

    public void start() {
        if (debug) Logger.debug("Debug mode is ON");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            if (debug) Logger.debug("Server socket created!");
            Logger.in("Router listening on port " + port + "...");

            do {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                    clientSocket.close();
                } catch (IOException e) {
                    Logger.err("Error handling client: " + e.getMessage());
                }
            } while (true);
        } catch (IOException e) {
            Logger.err("Router error: " + e.getMessage() + " : " + e.getCause());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }

            String method = Request.getType(requestLine);
            String pathp = Request.getPath(requestLine);
            String path = pathp.endsWith("/") ? pathp.substring(0, pathp.length() - 1) : pathp;

            if (debug) Logger.debug("Received request: " + method + " " + path);

            String routeMode = isMediaRouteOrRegularRoute(path);
            Response result = new Response();

            if (routeMode.equals("Route")){
                Route route = findRouteByPath(path);

                if (route != null) {
                    Handler handler = route.getHandler();
                    result = handleRequest(handler, method, clientSocket);
                } else {
                    Logger.warn("No route found for path: " + path);
                    result.setStatus(HttpStatus.NOT_FOUND);
                    result.setContentType(HttpContentTypes.TEXT_HTML);
                    result.setResponseBody("<html><body><h1>404 Not Found</h1></body></html>");
                }
                out.write(result.generate().getBytes());
            } else if (routeMode.equals("MediaRoute")){
                MediaRoute mediaRoute = findMediaRouteByPath(path);
                if (mediaRoute != null){
                    Handler handler = mediaRoute.getHandler();
                    result = handleRequest(handler, method, clientSocket);
                } else {
                    Logger.warn("No media-route found for path: " + path);
                    result.setStatus(HttpStatus.NOT_FOUND);
                    result.setContentType(HttpContentTypes.TEXT_HTML);
                    result.setResponseBody("<html><body><h1>404 Not Found</h1></body></html>");
                }
                out.write(result.generateFileResponseBytes());
            } else {
                Logger.warn("No route/media-route/handler(s) found for path: " + path);
                result.setStatus(HttpStatus.NOT_FOUND);
                result.setContentType(HttpContentTypes.TEXT_HTML);
                result.setResponseBody("<html><body><h1>404 Not Found</h1></body></html>");
                out.write(result.generate().getBytes());
            }

            out.flush();

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

    private MediaRoute findMediaRouteByPath(String path) {
        for (Map.Entry<MediaRoute, String> entry : mediaRoutes.entrySet()) {
            if (entry.getValue().equals(path)) {
                return entry.getKey();
            }
        }

        return null;
    }

    private String isMediaRouteOrRegularRoute(String path) {
        for (MediaRoute mediaRoute : mediaRoutes.keySet()) {
            if (mediaRoute.getPath().equals(path)) {
                return "MediaRoute";
            }
        }

        for (Route route : routes.keySet()) {
            if (route.getPath().equals(path)) {
                return "Route";
            }
        }

        return "NotFound";
    }

    private Response handleRequest(Handler handler, String method, Socket client) {
        if (!handler.acceptsClient(client)) {
            Response response = new Response();
            response.setStatus(HttpStatus.FORBIDDEN);
            response.setContentType(HttpContentTypes.TEXT_HTML);
            response.setResponseBody("<html><body><h1>Forbidden</h1></body></html>");
            return response;
        }

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

package qu.cipherr.QServer.Objects;

import qu.cipherr.QServer.Interfaces.Handler;

/**
 * Represents a route that handles requests for a specific path.
 */
public class Route {
    private String path;
    private Handler handler;

    public Route(String path, Handler handler) {
        this.path = path;
        this.handler = handler;
    }

    public String getPath() {
        return path;
    }

    public Handler getHandler() {
        return handler;
    }
}
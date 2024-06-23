# Route
For creating routes
### Example
```java
Route route = new Route("/", new MyHandler());
```
MyHandler.java:
```java
import qu.cipherr.QServer.Extra.HttpContentTypes;
import qu.cipherr.QServer.Extra.HttpStatus;
import qu.cipherr.QServer.Interfaces.Handler;
import qu.cipherr.QServer.Objects.Response;

import java.net.Socket;

public class MyHandler implements Handler {
    @Override
    public boolean acceptsClient(Socket clientSocket) {
        return true;
    }

    @Override
    public Response handleGet() {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>Hello, World!</h1></body></html>");
        return response;
    }

    @Override
    public Response handlePost() {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>POST Handled for /</h1></body></html>");
        return response;
    }

    @Override
    public Response handlePut() {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>PUT Handled for /</h1></body></html>");
        return response;
    }

    @Override
    public Response handleDelete() {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentTypes.TEXT_HTML);
        response.setResponseBody("<html><body><h1>DELETE Handled for /</h1></body></html>");
        return response;
    }
}
```

# MediaRoute
For creating asset paths
### Example:
```java
MediaRoute mediaRoute = new MediaRoute("/favicon.ico", new File("/path/to/file.png"), HttpContentTypes.IMAGE_PNG); // Can be other types
```

-----

## For adding routes/media-routes to Router, you can use the `addRoute()` and `addMediaRoute()` methods:
```java
Router s = new Router(1025, true);
s.addRoute(route);
s.addMediaRoute(mediaRoute);
s.start();
```

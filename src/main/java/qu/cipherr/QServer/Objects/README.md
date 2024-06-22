# Route
This class is for creating different routes (= paths) and handling them
### Parameters
* Path : String ("/", "/path")
* handler : [Handler](https://github.com/qu-cipher/QServer/blob/main/src/main/java/qu/cipherr/QServer/Interfaces/Handler.java)
### Example:
* MyHandler.java:
```java
public class MyHandler implements Handler {
    @Override
    public boolean acceptsClient(Socket clientSocket) {
        return false;
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

* Route creation:
```java
MyHandler handle = new MyHandler();
Route route = new Route("/path", handle);
```

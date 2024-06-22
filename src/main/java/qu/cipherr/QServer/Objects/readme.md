# Route
## Usage
The `Route` class is for creating Routes (= paths) and handling them through your handler class.
## Parameters:
* Path : String ("/" or "/path")
* handler : [Handler](https://github.com/qu-cipher/QServer/blob/main/src/main/java/qu/cipherr/QServer/Interfaces/Handler.java)
## Example | Route class
```java
MyHandler h = new MyHandler(); // The handler class
Route route = new Route("/path", h); // The route class
```
## Example | Handler interface implementation
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

#

# Response
## Usage
The `Response` class is for creating responses to clients which sent requests to `Router`
## Example:
```java
Response response = new Response();
response.setResponseBody("BODY");
response.setContentType(HttpContentTypes.TEXT_PLAIN); // Or others in HttpContentTypes class
response.setStatus(HttpStatus.OK); // or others in HttpStatus class
String res = response.generate();
```


# Request

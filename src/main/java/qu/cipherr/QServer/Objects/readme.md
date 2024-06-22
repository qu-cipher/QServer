# Route
## Usage
The `Route` class is for creating Routes (= paths) and handling them through your handler class.
## Parameters:
* Path : String ("/" or "/path")
* handler : [Handler](https://github.com/qu-cipher/QServer/blob/main/src/main/java/qu/cipherr/QServer/Interfaces/Handler.java)
## Example
```java
MyHandler h = new MyHandler(); // The handler class
Route route = new Route("/path", h); // The route class
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

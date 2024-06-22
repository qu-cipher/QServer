# Route
## Usage
The `Route` class is for creating Routes (= paths) and handling them through your handler class.
### Parameters:
* Path : String ("/" or "/path")
* handler : Handler  

#

# Response
## Usage
The `Response` class is for creating responses to clients which sent requests to `Router`
## Example:
```java
Response response = new Response();
response.setResponseBody("BODY");
response.setContentType(HttpContentTypes.TEXT_PLAIN); // Or others in [HttpContentTypes](https://github.com/qu-cipher/QServer/blob/main/src/main/java/qu/cipherr/QServer/Extra/HttpContentTypes.java)
response.setStatus(HttpStatus.OK); // or others in [HttpStatus](https://github.com/qu-cipher/QServer/blob/main/src/main/java/qu/cipherr/QServer/Extra/HttpStatus.java)
String res = response.generate();
```


# Request

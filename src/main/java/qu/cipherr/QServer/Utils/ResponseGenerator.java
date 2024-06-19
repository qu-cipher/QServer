package qu.cipherr.QServer.Utils;

public class ResponseGenerator {
    private String status = "HTTP/1.1 200 OK";
    private String contentType = "text/html";
    private String responseBody = "";

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String generate() {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append(status).append("\r\n")
                       .append("Content-Length: ").append(responseBody.length()).append("\r\n")
                       .append("Content-Type: ").append(contentType).append("\r\n")
                       .append("\r\n")
                       .append(responseBody);
        
        return responseBuilder.toString();
    }
}

package qu.cipherr.QServer.Utils;

public class Response {
    private String status;
    private String contentType;
    private String responseBody;

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
        StringBuilder response = new StringBuilder();
        response.append(status).append("\r\n");
        response.append("Content-Type: ").append(contentType).append("\r\n");
        response.append("Content-Length: ").append(responseBody.length()).append("\r\n");
        response.append("\r\n");
        response.append(responseBody);
        return response.toString();
    }
}

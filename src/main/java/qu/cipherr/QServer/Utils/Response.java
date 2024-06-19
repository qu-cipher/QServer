package qu.cipherr.QServer.Utils;

public class Response {
    private ResponseGenerator responseGenerator;

    public Response() {
        this.responseGenerator = new ResponseGenerator();
    }

    public ResponseGenerator getResponseGenerator() {
        return responseGenerator;
    }

    public void setStatus(String status) {
        this.responseGenerator.setStatus(status);
    }

    public void setContentType(String contentType) {
        this.responseGenerator.setContentType(contentType);
    }

    public void setResponseBody(String responseBody) {
        this.responseGenerator.setResponseBody(responseBody);
    }

    public String generate() {
        return this.responseGenerator.generate();
    }
}

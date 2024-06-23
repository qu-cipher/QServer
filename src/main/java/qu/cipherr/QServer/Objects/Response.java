package qu.cipherr.QServer.Objects;

public class Response {
    private String status;
    private String contentType;
    private String responseBody;
    private byte[] responseFile;
    private String responseType;

    public void setResponseType(String type) {
        this.responseType = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public void setResponseFile(byte[] responseFile) {
        this.responseFile = responseFile;
    }

    public String generate() {
        if ("File".equals(responseType)) {
            return generateFileResponse();
        } else {
            return generateStringResponse();
        }
    }

    private String generateStringResponse() {
        StringBuilder response = new StringBuilder();
        response.append(status).append("\r\n");
        response.append("Content-Type: ").append(contentType).append("\r\n");
        response.append("Content-Length: ").append(responseBody.length()).append("\r\n");
        response.append("\r\n");
        response.append(responseBody);
        return response.toString();
    }

    private String generateFileResponse() {
        StringBuilder response = new StringBuilder();
        response.append(status).append("\r\n");
        response.append("Content-Type: ").append(contentType).append("\r\n");
        response.append("Content-Length: ").append(responseFile.length).append("\r\n");
        response.append("\r\n");
        return response.toString();
    }

    public byte[] generateFileResponseBytes() {
        String headers = generateFileResponse();
        byte[] headerBytes = headers.getBytes();
        byte[] fullResponse = new byte[headerBytes.length + responseFile.length];

        System.arraycopy(headerBytes, 0, fullResponse, 0, headerBytes.length);
        System.arraycopy(responseFile, 0, fullResponse, headerBytes.length, responseFile.length);

        return fullResponse;
    }
}

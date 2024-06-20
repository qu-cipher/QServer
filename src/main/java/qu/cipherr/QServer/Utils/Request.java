package qu.cipherr.QServer.Utils;

public class Request {

    public static String getType(String requestLine) {
        if (requestLine != null && !requestLine.isEmpty()) {
            return requestLine.split(" ")[0];
        }
        return "None";
    }

    public static String getPath(String requestLine) {
        if (requestLine != null && !requestLine.isEmpty()) {
            String[] parts = requestLine.split(" ");
            if (parts.length > 1) {
                return parts[1];
            }
        }
        return "/";
    }
}

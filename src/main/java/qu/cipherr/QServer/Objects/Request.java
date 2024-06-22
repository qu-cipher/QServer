package qu.cipherr.QServer.Objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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

    public static Map getHeaders(Socket client) throws IOException {
        StringBuffer result = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        Map<String, String> headers = new HashMap<>();
        String headerLine;

        while ((headerLine = in.readLine()) != null && !headerLine.isEmpty()) {
            String[] headerParts = headerLine.split(":", 2);
            if (headerParts.length == 2) {
                headers.put(headerParts[0].trim(), headerParts[1].trim());
            }
        }

        return headers;
    }
}
